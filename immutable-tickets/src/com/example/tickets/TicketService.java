package com.example.tickets;

import java.util.*;

public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // I removed the scattered validation from here because it's now handled inside Builder.build().
        List<String> initialTags = new ArrayList<>();
        initialTags.add("NEW");

        // I am using the Builder to create the initial object in one fluent call.
        return IncidentTicket.builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .tags(initialTags)
                .build();
    }

    // I refactored this to return a NEW instance. The original ticket 't' remains unchanged.
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        List<String> newTags = new ArrayList<>(t.getTags());
        newTags.add("ESCALATED");

        return t.toBuilder()
                .priority("CRITICAL")
                .tags(newTags)
                .build();
    }

    // I refactored this to use the Builder copy mechanism to "update" the assignee.
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}

/* Initial code
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // scattered validation (incomplete on purpose)
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("id required");
        if (reporterEmail == null || !reporterEmail.contains("@")) throw new IllegalArgumentException("email invalid");
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("title required");

        IncidentTicket t = new IncidentTicket(id, reporterEmail, title);

        // BAD: mutating after creation
        t.setPriority("MEDIUM");
        t.setSource("CLI");
        t.setCustomerVisible(false);

        List<String> tags = new ArrayList<>();
        tags.add("NEW");
        t.setTags(tags);

        return t;
    }

    public void escalateToCritical(IncidentTicket t) {
        // BAD: mutating ticket after it has been "created"
        t.setPriority("CRITICAL");
        t.getTags().add("ESCALATED"); // list leak
    }

    public void assign(IncidentTicket t, String assigneeEmail) {
        // scattered validation
        if (assigneeEmail != null && !assigneeEmail.contains("@")) {
            throw new IllegalArgumentException("assigneeEmail invalid");
        }
        t.setAssigneeEmail(assigneeEmail);
    }
}
*/