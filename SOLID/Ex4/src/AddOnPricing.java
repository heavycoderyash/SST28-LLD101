import java.util.Map;

public class AddOnPricing implements PricingComponent {
    private final Map<AddOn, Double> rates;

    public AddOnPricing(Map<AddOn, Double> rates) { this.rates = rates; }

    @Override
    public Money getPrice(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += rates.getOrDefault(a, 0.0);
        }
        return new Money(total);
    }
}