package com.example.payments;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> gateways = new HashMap<>();
        
        // I am now registering the Adapters instead of the raw SDK clients.
        // This provides the OrderService with a uniform interface to work with.
        gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
        gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));
        
        OrderService svc = new OrderService(gateways);

        String id1 = svc.charge("fastpay", "cust-1", 1299);
        String id2 = svc.charge("safecash", "cust-2", 1299);
        
        System.out.println(id1);
        System.out.println(id2);
    }
}

/* Initial code
public static void main(String[] args) {
    Map<String, PaymentGateway> gateways = new HashMap<>();
    // TODO: register adapters instead of raw SDKs
    // gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
    // gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));
    OrderService svc = new OrderService(gateways);

    String id1 = svc.charge("fastpay", "cust-1", 1299);
    String id2 = svc.charge("safecash", "cust-2", 1299);
    System.out.println(id1);
    System.out.println(id2);
}
*/