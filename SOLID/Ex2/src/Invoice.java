import java.util.List;

public class Invoice {
    public final String id;
    public final List<String> details;
    public final double subtotal, taxPct, tax, discount, total;

    public Invoice(String id, List<String> details, double subtotal, double taxPct, double tax, double discount, double total) {
        this.id = id; 
        this.details = details; 
        this.subtotal = subtotal;
        this.taxPct = taxPct; 
        this.tax = tax; 
        this.discount = discount; 
        this.total = total;
    }
}