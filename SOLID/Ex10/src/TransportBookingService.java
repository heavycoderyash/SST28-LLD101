public class TransportBookingService {
    // I replaced concrete classes with interfaces to follow DIP. 
    private final IDistanceCalculator distanceCalculator;
    private final IDriverAllocator driverAllocator;
    private final IPaymentGateway paymentGateway;

    // It now uses constructor injection. 
    // The dependencies are provided to it making it much easier to test and maintain.
    public TransportBookingService(
        IDistanceCalculator distanceCalculator,
        IDriverAllocator driverAllocator,
        IPaymentGateway paymentGateway
    ) {
        this.distanceCalculator = distanceCalculator;
        this.driverAllocator = driverAllocator;
        this.paymentGateway = paymentGateway;
    }

    public void book(TripRequest req) {
        // I removed the new keywords here. I use the injected instances instead.
        double km = distanceCalculator.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverAllocator.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667;
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentGateway.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}

/* initial code
public class TransportBookingService {
    // DIP violation: direct concretes
    public void book(TripRequest req) {
        DistanceCalculator dist = new DistanceCalculator();
        DriverAllocator alloc = new DriverAllocator();
        PaymentGateway pay = new PaymentGateway();

        double km = dist.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = alloc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = pay.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
*/