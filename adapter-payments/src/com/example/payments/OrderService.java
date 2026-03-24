package com.example.payments;

import java.util.*;

public class OrderService {
    private final Map<String, PaymentGateway> gateways;

    public OrderService(Map<String, PaymentGateway> gateways) {
        this.gateways = Objects.requireNonNull(gateways, "gateways");
    }

    public String charge(String provider, String customerId, int amountCents) {
        Objects.requireNonNull(provider, "provider");
        
        // I removed the manual branching logic. 
        // Now I simply look up the implementation in the map. 
        // This makes the code open-closed 
        // I can add new providers without ever changing this method again.
        PaymentGateway gw = gateways.get(provider);
        
        if (gw == null) throw new IllegalArgumentException("unknown provider: " + provider);
        
        return gw.charge(customerId, amountCents);
    }
}

/* INITIAL CODE
public String charge(String provider, String customerId, int amountCents) {
    Objects.requireNonNull(provider, "provider");
    PaymentGateway gw = gateways.get(provider);
    if (gw == null) throw new IllegalArgumentException("unknown provider: " + provider);
    return gw.charge(customerId, amountCents);
}
*/