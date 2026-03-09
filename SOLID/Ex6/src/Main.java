import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        // I put the senders in a list to get substitutability.
        // The loop now treats every sender exactly the same way without knowing its specific class.
        List<NotificationSender> senders = new ArrayList<>();
        senders.add(new EmailSender(audit));
        senders.add(new SmsSender(audit));
        senders.add(new WhatsAppSender(audit));

        for (NotificationSender sender : senders) {
            if (sender.canSend(n)) {
                sender.send(n);
            } else {
                // I replaced the try-catch block with a check.
                // This satisfies LSP because the caller no longer needs subtype-specific logic to handle special requirements.
                System.out.println("WA ERROR: phone must start with + and country code");
                audit.add("WA failed");
            }
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}

/* initial code
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        email.send(n);
        sms.send(n);
        try {
            wa.send(n);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
*/