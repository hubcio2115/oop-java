package org.example.lab07_12;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Calendar {
    private ArrayList<ArrayList<CalendarEntry>> entries;

    public final int NUMBER_OF_WEEKS;

    public final int NUMBER_OF_DAYS_IN_WEEK = 7;

    public Calendar() {
        this(1);
    }

    public Calendar(int numberOfWeeks) {
        if (numberOfWeeks < 1)
            throw new IllegalArgumentException("Numer tygodni musi być większy od 1!");

        this.NUMBER_OF_WEEKS = numberOfWeeks;
        this.entries = new ArrayList<>(this.NUMBER_OF_WEEKS);

        for (var i = 0; i < this.NUMBER_OF_DAYS_IN_WEEK * this.NUMBER_OF_WEEKS; i++)
            this.entries.add(new ArrayList<>());
    }

    public void addEntry(int day, String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.entries.get(day - 1).add(new Meeting(description, startTime, endTime, priority));
    }

    public void addEntry(int day, String description, LocalTime startTime, LocalTime endTime, Status status) {
        this.entries.get(day - 1).add(new Task(description, startTime, endTime, status));
    }

    public void removeMeeting(int day, int index) {
        this.entries.get(day - 1).remove(index - 1);
        System.out.println("Nie ma spotkania o podanym indeksie tego dnia!");
    }

    private int getIndexOfAnEntryInADay(int day, CalendarEntry entry) {
        var entriesDuringADay = this.filterEntriesDuringADay(day, (filteredEntry) -> true);

        for (var i = 0; i < entriesDuringADay.size(); i++)
            if (entry == entriesDuringADay.get(i)) return i + 1;


        throw new NoSuchElementException();
    }

    private void printEntries(int day, ArrayList<CalendarEntry> entries) {
        for (var i = 0; i < entries.size(); i++) {
            var index = this.getIndexOfAnEntryInADay(day, entries.get(i));
            System.out.printf("Indeks: %d\n" + "%s", index, entries.toString());
        }
    }

    public void printMeetingsDuringADay(int day) {
        var meetingsInADay = this.filterEntriesDuringADay(day, (entry) -> entry instanceof Meeting);
        this.printEntries(day, meetingsInADay);
    }

    public void printMeetingsDuringADay(int day, Priority priority) {
        var meetingsInADay = this.filterEntriesDuringADay(day, (entry) -> entry instanceof Meeting && ((Meeting) entry).getPriority() == priority);
        this.printEntries(day, meetingsInADay);
    }

    public void printMeetingsDuringADay(int day, LocalTime startTime) {
        var meetingsInADay = this.filterEntriesDuringADay(day, (entry -> entry instanceof Meeting && entry.getStartTime().isAfter(startTime)));
        this.printEntries(day, meetingsInADay);
    }

    public void printMeetingsDuringADay(int day, LocalTime startTime, LocalTime endTime) {
        var meetingsInADay = this.filterEntriesDuringADay(day, entry -> entry instanceof Meeting && entry.getStartTime().isAfter(startTime) && entry.getStartTime().isBefore(endTime));
        this.printEntries(day, meetingsInADay);
    }

    public void printMeetingsDuringADay(int day, Priority priority, LocalTime startTime) {
        var meetingsInADay = this.filterEntriesDuringADay(day, entry -> entry instanceof Meeting && entry.getStartTime().isAfter(startTime) && ((Meeting) entry).getPriority() == priority);
        this.printEntries(day, meetingsInADay);
    }

    public void printTasksDuringADay(int day) {
        var tasksInADay = this.filterEntriesDuringADay(day, (entry) -> entry instanceof Task);
        this.printEntries(day, tasksInADay);
    }

    public void printTasksDuringADay(int day, Status status) {
        var tasksInADay = this.filterEntriesDuringADay(day, (entry) -> entry instanceof Task && ((Task) entry).getStatus() == status);
        this.printEntries(day, tasksInADay);
    }

    public void printTasksDuringADay(int day, Status status, LocalTime endTime) {
        var tasksInADay = this.filterEntriesDuringADay(day, (entry) -> entry instanceof Task && ((Task) entry).getStatus() == status && entry.getEndTime().isBefore(endTime));
        this.printEntries(day, tasksInADay);
    }

    private ArrayList<CalendarEntry> filterEntriesDuringADay(int day, Predicate<CalendarEntry> predicate) {
        ArrayList<CalendarEntry> filteredEntries = new ArrayList<>();
        var entriesInADay = this.entries.get(day);

        for (var i = 0; i < entriesInADay.size(); i++)
            if (predicate.test(entriesInADay.get(i)))
                filteredEntries.add(this.entries.get(i).get(i));

        return filteredEntries;
    }
}
