package org.example.lab07_12;

import java.time.DateTimeException;
import java.time.LocalTime;

public abstract sealed class CalendarEntry permits Meeting, Task {
    protected String description;
    protected LocalTime startTime;
    protected LocalTime endTime;

    static final LocalTime MIN_START_DATE = LocalTime.of(7, 59, 59);

    protected CalendarEntry(String description, LocalTime startTime, LocalTime endTime) {
        if (startTime.isBefore(MIN_START_DATE))
            throw new DateTimeException("Czas początkowy musi być później niż 8:00");

        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public String toString() {
        return null;
    }

    protected LocalTime getStartTime() {
        return this.startTime;
    }

    protected LocalTime getEndTime() {
        return this.endTime;
    }

}
