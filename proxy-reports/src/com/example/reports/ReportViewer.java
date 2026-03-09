package com.example.reports;

public class ReportViewer {

    // I changed the parameter from ReportFile to the Report interface.
    // This allows the viewer to work with both proxy and real objects without knowing which is which.
    public void open(Report report, User user) {
        report.display(user);
    }
}

/* Initial code
public class ReportViewer {
    public void open(ReportFile report, User user) {
        report.display(user);
    }
}
*/