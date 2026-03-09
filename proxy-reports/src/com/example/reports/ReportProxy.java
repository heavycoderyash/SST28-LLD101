package com.example.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    
    // I added a reference to RealReport to enable caching/lazy loading.
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // I first verify access so we dont load data for unauthorized users.
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: User " + user.getName() + 
                               " cannot view " + classification + " reports.");
            return;
        }

        // I implement lazy loading and caching here
        // If its the first time and authorized then I create the RealReport.
        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }
        
        // I delegate the actual display work to the real object.
        realReport.display(user);
    }
}

/* Initial code
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Starter placeholder: intentionally incorrect.
        // Students should remove direct real loading on every call.
        RealReport report = new RealReport(reportId, title, classification);
        report.display(user);
    }
}
*/