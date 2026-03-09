public interface PricingComponent {
    Money getPrice(BookingRequest req);
}