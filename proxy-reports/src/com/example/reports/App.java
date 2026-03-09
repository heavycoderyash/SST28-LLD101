package com.example.reports;

public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // I switched these from ReportFile to ReportProxy.
        // Now creating these objects is cheap because no disk loading happens yet.
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        viewer.open(publicReport, student);
        System.out.println();

        viewer.open(facultyReport, student);
        System.out.println();

        viewer.open(facultyReport, faculty);
        System.out.println();

        viewer.open(adminReport, admin);
        System.out.println();

        viewer.open(adminReport, admin);
    }
}

/* Initial code
public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        ReportFile publicReport = new ReportFile("R-101", "Orientation Plan", "PUBLIC");
        ReportFile facultyReport = new ReportFile("R-202", "Midterm Review", "FACULTY");
        ReportFile adminReport = new ReportFile("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        viewer.open(publicReport, student);
        System.out.println();

        viewer.open(facultyReport, student);
        System.out.println();

        viewer.open(facultyReport, faculty);
        System.out.println();

        viewer.open(adminReport, admin);
        System.out.println();

        viewer.open(adminReport, admin);
    }
}
*/