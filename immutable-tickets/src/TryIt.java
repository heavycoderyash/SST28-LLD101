import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.*;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // I am capturing the initial ticket.
        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing");
        System.out.println("Created: " + t1);

        // I updated this to show that service methods now return new objects.
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);
        
        System.out.println("\nOriginal ticket remains unchanged: " + t1);
        System.out.println("Final state (new object): " + t3);

        // I am showing that the list reference is no longer a leak.
        try {
            List<String> tags = t3.getTags();
            tags.add("HACKED"); 
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccess: Internal tags list is protected and cannot be modified!");
        }
    }
}

/* Initial code
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate post-creation mutation through service
        service.assign(t, "agent@example.com");
        service.escalateToCritical(t);
        System.out.println("\nAfter service mutations: " + t);

        // Demonstrate external mutation via leaked list reference
        List<String> tags = t.getTags();
        tags.add("HACKED_FROM_OUTSIDE");
        System.out.println("\nAfter external tag mutation: " + t);

        // Starter compiles; after refactor, you should redesign updates to create new objects instead.
    }
}
*/