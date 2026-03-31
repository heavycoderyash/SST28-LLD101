package com.example.payments;

import java.util.*;

public class App {
    public static void main(String[] args) {;
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

// The original code suffered from tight coupling. The OrderService was directly dependent on two completely different sdk
// FastPay had a one-step method, while SafeCash required a multi-step process
// OrderService used a string-based switch to decide how to handle each provider.
// This setup violated the ocp. To add a new payment method, you would have to break the existing OrderService code to add more logic.

// I solved this by implementing the adapter pattern. 
// This acts as a middleman that translates the specific, messy logic of each sdk into a single interface called PaymentGateway
// OrderService now only interacts with the PaymentGateway interface. It calls one method: charge()
// the glue logic is now hidden inside adapter class