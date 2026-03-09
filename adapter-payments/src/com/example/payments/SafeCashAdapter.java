package com.example.payments;

public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // SafeCash has a two-step process- create then confirm.
        // I am encapsulating this complexity here so the OrderService doesnt have to know about SafeCashPayment objects.
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}