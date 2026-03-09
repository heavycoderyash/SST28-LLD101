public class StudentPolicies {
    public static class StudentTax implements TaxPolicy {
        @Override
        public double getTaxPercent() { return 5.0; }
    }

    public static class StudentDiscount implements DiscountPolicy {
        @Override
        public double calculateDiscount(double subtotal, int lineCount) {
            return (subtotal >= 180.0) ? 10.0 : 0.0;
        }
    }
}