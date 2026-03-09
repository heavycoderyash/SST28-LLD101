import java.util.Map;

public class RoomPricing implements PricingComponent {
    private final Map<Integer, Double> rates;

    public RoomPricing(Map<Integer, Double> rates) { this.rates = rates; }

    @Override
    public Money getPrice(BookingRequest req) {
        double amount = rates.getOrDefault(req.roomType, 16000.0);
        return new Money(amount);
    }
}