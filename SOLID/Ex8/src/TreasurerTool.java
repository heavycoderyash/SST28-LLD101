public class TreasurerTool implements FinanceTools {
    private final BudgetLedger ledger;

    public TreasurerTool(BudgetLedger ledger) { 
        this.ledger = ledger; 
    }

    @Override 
    public void addIncome(double amt, String note) { 
        ledger.add(amt, note); 
    }

    @Override 
    public void addExpense(double amt, String note) { 
        ledger.add(-amt, note); 
    }

    // I removed the dummy addMinutes, createEvent and getEventsCount methods.
    // Since I now only implement FinanceTools I no longer have to carry code that does nothing or returns 0.
}

/* initial code
public class TreasurerTool implements ClubAdminTools {
    private final BudgetLedger ledger;
    public TreasurerTool(BudgetLedger ledger) { this.ledger = ledger; }
    @Override public void addIncome(double amt, String note) { ledger.add(amt, note); }
    @Override public void addExpense(double amt, String note) { ledger.add(-amt, note); }
    @Override public void addMinutes(String text) { }
    @Override public void createEvent(String name, double budget) { }
    @Override public int getEventsCount() { return 0; } 
}
*/