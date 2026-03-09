public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    // I added this method to make preconditions explicit. 
    // Now the caller can check if a sender is compatible with a notification before attempting to send it satisfying LSP.
    public abstract boolean canSend(Notification n);

    public abstract void send(Notification n);
}

/* initial code
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public abstract void send(Notification n);
}
*/