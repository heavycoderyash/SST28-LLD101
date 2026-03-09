package com.example.reports;

public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private String cachedContent;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        // I call the expensive load here so that the proxy only creates this object when its absolutely ready to show data.
        this.cachedContent = loadFromDisk();
    }

    @Override
    public void display(User user) {
        // This only prints the data.
        // The loading logic is isolated.
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + cachedContent);
    }

    private String loadFromDisk() {
        // I moved this simulation here because RealReport is the only class that should handle raw data retreival.
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }
}

/* Initial code
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        System.out.println("TODO: implement via real loading");
    }

    public String getClassification() {
        return classification;
    }
}
*/