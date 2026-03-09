public class InvoiceFormatter {
    public static String format(Invoice inv) {
        StringBuilder sb = new StringBuilder("=== Cafeteria Billing ===\n");
        sb.append("Invoice# ").append(inv.id).append("\n");
        for (String line : inv.details) {
            sb.append("- ").append(line).append("\n");
        }
        sb.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPct, inv.tax));
        sb.append(String.format("Discount: -%.2f\n", inv.discount));
        sb.append(String.format("TOTAL: %.2f\n", inv.total));
        return sb.toString();
    }
}