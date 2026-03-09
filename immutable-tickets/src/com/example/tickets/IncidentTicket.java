package com.example.tickets;

import java.util.*;

public class IncidentTicket {

    // I changed these fields to 'final' to ensure they cannot be reassigned after the object is created.
    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    // I made the constructor private so that the only way to create a ticket is through the Builder.
    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        // I am creating a new list and wrapping it in 'unmodifiableList' to prevent external mutation.
        this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    
    // I am returning the unmodifiable list directly here since it was already protected in the constructor.
    public List<String> getTags() { return tags; }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    // I added this method so we can "copy" an existing ticket into a builder to perform updates.
    public static Builder builder(String id, String reporterEmail, String title) {
        return new Builder(id, reporterEmail, title);
    }

    public Builder toBuilder() {
        return new Builder(this.id, this.reporterEmail, this.title)
                .description(this.description)
                .priority(this.priority)
                .tags(new ArrayList<>(this.tags))
                .assigneeEmail(this.assigneeEmail)
                .customerVisible(this.customerVisible)
                .slaMinutes(this.slaMinutes)
                .source(this.source);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" + "id='" + id + '\'' + ", tags=" + tags + ", priority='" + priority + '\'' + ", assignee='" + assigneeEmail + '\'' + '}';
    }

    // I introduced this Static Inner Builder to handle the complex construction and validation.
    public static class Builder {
        private final String id;
        private final String reporterEmail;
        private final String title;
        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        // I require the core fields in the constructor to ensure a ticket is never "half-baked".
        public Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        public Builder description(String d) { this.description = d; return this; }
        public Builder priority(String p) { this.priority = p; return this; }
        public Builder tags(List<String> t) { this.tags = (t != null) ? t : new ArrayList<>(); return this; }
        public Builder assigneeEmail(String e) { this.assigneeEmail = e; return this; }
        public Builder customerVisible(boolean v) { this.customerVisible = v; return this; }
        public Builder slaMinutes(Integer s) { this.slaMinutes = s; return this; }
        public Builder source(String s) { this.source = s; return this; }

        // I centralized ALL validation logic here. If this method finishes, the object is guaranteed valid.
        public IncidentTicket build() {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            if (assigneeEmail != null) Validation.requireEmail(assigneeEmail, "assigneeEmail");
            
            return new IncidentTicket(this);
        }
    }
}

/* Initial code
public class IncidentTicket {

    private String id;
    private String reporterEmail;
    private String title;

    private String description;
    private String priority;       // LOW, MEDIUM, HIGH, CRITICAL
    private List<String> tags;     // mutable leak
    private String assigneeEmail;
    private boolean customerVisible;
    private Integer slaMinutes;    // optional
    private String source;         // e.g. "CLI", "WEBHOOK", "EMAIL"

    public IncidentTicket() {
        this.tags = new ArrayList<>();
    }

    public IncidentTicket(String id, String reporterEmail, String title) {
        this();
        this.id = id;
        this.reporterEmail = reporterEmail;
        this.title = title;
    }

    public IncidentTicket(String id, String reporterEmail, String title, String priority) {
        this(id, reporterEmail, title);
        this.priority = priority;
    }

    // Getters
    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; } // BROKEN: leaks internal list
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    // Setters (BROKEN: should not exist after refactor)
    public void setId(String id) { this.id = id; }
    public void setReporterEmail(String reporterEmail) { this.reporterEmail = reporterEmail; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setTags(List<String> tags) { this.tags = tags; } // BROKEN: retains external reference
    public void setAssigneeEmail(String assigneeEmail) { this.assigneeEmail = assigneeEmail; }
    public void setCustomerVisible(boolean customerVisible) { this.customerVisible = customerVisible; }
    public void setSlaMinutes(Integer slaMinutes) { this.slaMinutes = slaMinutes; }
    public void setSource(String source) { this.source = source; }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
*/