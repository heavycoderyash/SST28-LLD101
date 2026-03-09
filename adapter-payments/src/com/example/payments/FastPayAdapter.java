package com.example.payments;

public class FastPayAdapter implements PaymentGateway {
    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // I am mapping the standard charge method to the specific payNow method 
        // required by the FastPay SDK.
        return client.payNow(customerId, amountCents);
    }
}