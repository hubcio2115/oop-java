package org.example.lab07_12;

import java.time.LocalTime;

public final class Task extends CalendarEntry {
    private Status status;

    public Task(String description, LocalTime startTime, LocalTime endTime, Status status) {
        super(description, startTime, endTime);
        this.status = status;
    }

    @Override
    public String toString() {
        return this.startTime.toString() + " - " + this.endTime.toString() + "\n" +
                "Status: " + this.status.stringValue + "\n" +
                "Description: " + this.description + "\n";
    }

    public Status getStatus() {
        return this.status;
    }
}
