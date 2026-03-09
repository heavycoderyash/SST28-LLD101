public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean canSend(Notification n) {
        // Email is generally flexible so we return true.
        return n.email != null && !n.email.isEmpty();
    }

    @Override
    public void send(Notification n) {
        // I removed the silent truncation. 
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}

/* initial code
public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        String body = n.body;
        if (body.length() > 40) body = body.substring(0, 40);
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}
*/
