// I split the giant ClubAdminTools interface into three specific capabilities.
// This ensures that a class like treasurer isnt forced to know about unrelated features like meeting minutes.
interface FinanceTools {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
}

interface MinutesTools {
    void addMinutes(String text);
}

interface EventTools {
    void createEvent(String name, double budget);
    int getEventsCount();
}

/* initial code
public interface ClubAdminTools {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
    void addMinutes(String text);
    void createEvent(String name, double budget);
    int getEventsCount();
}
*/