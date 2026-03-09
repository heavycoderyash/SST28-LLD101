public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public boolean canSend(Notification n) {
        return n.phone != null && !n.phone.isEmpty();
    }

    @Override
    public void send(Notification n) {
        // I updated this to include the subject if it exists. 
        // Ignoring the subject entirely when the base type accepts a "Notification" 
        // (which has a subject) was an LSP smell regarding unexpected behavior.
        String content = (n.subject != null && !n.subject.isEmpty()) 
                         ? "[" + n.subject + "] " + n.body 
                         : n.body;
        
        System.out.println("SMS -> to=" + n.phone + " body=" + content);
        audit.add("sms sent");
    }
}

/* initial code
public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
*/