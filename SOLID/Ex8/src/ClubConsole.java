public class ClubConsole {
    private final BudgetLedger ledger;
    private final MinutesBook minutes;
    private final EventPlanner events;

    public ClubConsole(BudgetLedger ledger, MinutesBook minutes, EventPlanner events) {
        this.ledger = ledger; 
        this.minutes = minutes; 
        this.events = events;
    }

    public void run() {
        // I updated these types to the new segregated interfaces.
        // Now treasurer can only call financial methods making the capablities clear.
        FinanceTools treasurer = new TreasurerTool(ledger);
        MinutesTools secretary = new SecretaryTool(minutes);
        EventTools lead = new EventLeadTool(events);

        treasurer.addIncome(5000, "sponsor");
        secretary.addMinutes("Meeting at 5pm");
        lead.createEvent("HackNight", 2000);

        System.out.println("Summary: ledgerBalance=" + ledger.balanceInt() + ", minutes=" + minutes.count() + ", events=" + lead.getEventsCount());
    }
}

/* initial code
public class ClubConsole {
    private final BudgetLedger ledger;
    private final MinutesBook minutes;
    private final EventPlanner events;
    public ClubConsole(BudgetLedger ledger, MinutesBook minutes, EventPlanner events) {
        this.ledger = ledger; this.minutes = minutes; this.events = events;
    }
    public void run() {
        ClubAdminTools treasurer = new TreasurerTool(ledger);
        ClubAdminTools secretary = new SecretaryTool(minutes);
        ClubAdminTools lead = new EventLeadTool(events);
        treasurer.addIncome(5000, "sponsor");
        secretary.addMinutes("Meeting at 5pm");
        lead.createEvent("HackNight", 2000);
        System.out.println("Summary: ledgerBalance=" + ledger.balanceInt() + ", minutes=" + minutes.count() + ", events=" + lead.getEventsCount());
    }
}
*/