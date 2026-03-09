public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean canSend(Notification n) {
        // I moved the validation logic here. 
        // This prevents the 'send' method from throwing a RuntimeException,
        // ensuring that this subtype does not "tighten" preconditions unexpectedly.
        return n.phone != null && n.phone.startsWith("+");
    }

    @Override
    public void send(Notification n) {
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}

/* initial code
public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
*/