package org.example.lab07_11;

import java.time.LocalTime;

public final class Meeting extends CalendarEntry {
    private final Priority priority;

    public Meeting(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        super(description, startTime, endTime);
        this.priority = priority;
    }

    @Override
    public String toString() {
        return this.startTime.toString() + " - " + this.endTime.toString() + "\n" +
                "Priority: " + this.priority.stringValue + "\n" +
                "Description: " + this.description + "\n";
    }

    public Priority getPriority() {
        return this.priority;
    }
}