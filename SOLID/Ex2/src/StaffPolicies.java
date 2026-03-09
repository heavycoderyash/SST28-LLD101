public class StaffPolicies {
    public static class StaffTax implements TaxPolicy {
        @Override
        public double getTaxPercent() { return 2.0; }
    }

    public static class StaffDiscount implements DiscountPolicy {
        @Override
        public double calculateDiscount(double subtotal, int distinctLines) {
            return (distinctLines >= 3) ? 15.0 : 5.0;
        }
    }
}