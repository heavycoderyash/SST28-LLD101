import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) { this.store = store; }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(TaxPolicy taxPolicy, DiscountPolicy discountPolicy, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        double subtotal = 0.0;
        List<String> details = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            details.add(String.format("%s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        double taxPct = taxPolicy.getTaxPercent();
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.calculateDiscount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        Invoice inv = new Invoice(invId, details, subtotal, taxPct, tax, discount, total);
        String printable = InvoiceFormatter.format(inv);

        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}