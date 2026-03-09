// By implementing IPaymentGateway it ensures that 
// booking service only knows how to charge, regardless of whether its via stripe,paypal,etc.
public class PaymentGateway implements IPaymentGateway {
    public String charge(String studentId, double amount) {
        return "TXN-9001";
    }
}

/* initial code
public class PaymentGateway {
    public String charge(String studentId, double amount) {
        // fake deterministic txn
        return "TXN-9001";
    }
}
*/