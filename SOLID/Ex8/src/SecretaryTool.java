public class SecretaryTool implements MinutesTools {
    private final MinutesBook book;

    public SecretaryTool(MinutesBook book) { 
        this.book = book; 
    }

    @Override 
    public void addMinutes(String text) { 
        book.add(text); 
    }

    // I removed the financial and event methods here. 
    // The SecretaryTool is now clean and only contains logic relevant to its role.
}

/* initial code
public class SecretaryTool implements ClubAdminTools {
    private final MinutesBook book;
    public SecretaryTool(MinutesBook book) { this.book = book; }
    @Override public void addMinutes(String text) { book.add(text); }
    @Override public void addIncome(double amt, String note) { }
    @Override public void addExpense(double amt, String note) { }
    @Override public void createEvent(String name, double budget) { }
    @Override public int getEventsCount() { return 0; }
}
*/