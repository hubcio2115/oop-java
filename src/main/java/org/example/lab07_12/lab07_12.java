package org.example.lab07_12;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class lab07_12 {
    public static void main(String[] args) {
        System.out.println("Program służy do zarządzania kalendarzem spotkań dla jednego tygodnia");
        Scanner scanner = new Scanner(System.in);

        var numberOfWeeks = 0;
        boolean didUserProvidedValidNumberOfWeeks = false;
        while (!didUserProvidedValidNumberOfWeeks)
            try {
                numberOfWeeks = Integer.parseInt(getValueFromUser(scanner, "Podaj wartość dni tygodnia w której zakresie kalendarz ma funkcjonować (eq. 2): "));
                didUserProvidedValidNumberOfWeeks = true;
            } catch (NumberFormatException e) {
                System.out.println("Podana liczba nie może zostać zinterpretowana jako liczba całkowita!");
            }


        var calendar = new Calendar(numberOfWeeks);

        var isRunning = true;
        while (isRunning) {
            printMenu();

            char option = scanner.next().toCharArray()[0];
            try {
                switch (option) {
                    case 'a' -> addMeeting(scanner, calendar);

                    case 'b' -> addTask(scanner, calendar);

                    case 'c' -> deleteEntry(scanner, calendar);

                    case 'd' -> printMeetingsDuringADay(scanner, calendar);

                    case 'e' -> printTasksDuringADay(scanner, calendar);

                    case 'f' -> printMeetingsDuringADayWithPriority(scanner, calendar);

                    case 'g' -> printTasksDuringADayWithStatus(scanner, calendar);

                    case 'h' -> printMeetingsDuringADayAfterTime(scanner, calendar);

                    case 'i' -> printMeetingsDuringADayInBetweenTime(scanner, calendar);

                    case 'j' -> printMeetingsDuringADayAfterTimeWithPriority(scanner, calendar);

                    case 'k' -> printTasksDuringADayWithStatusBeforeEndTime(scanner, calendar);

                    case 'l' -> isRunning = false;

                    default -> System.out.println("Wybrałeś złą opcję!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nie ma takiego indeksu dnia w kalendarzu!");
            } catch (NumberFormatException e) {
                System.out.println("Podano wartość, której nie można zinterpretować jako numer!");
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Priorytet może być niski, wysoki lub średni!");
            }
        }
    }

    public static String getValueFromUser(Scanner scanner, String message) {
        System.out.print(message);

        String value;
        value = scanner.next();

        return value;
    }

    public static LocalTime getLocalTimeFromUser(Scanner scanner, String message) {
        var value = getValueFromUser(scanner, message);
        var timePattern = Pattern.compile("^([0-1][0-9])|(2[0-4]):[0-5][0-9]$");

        if (timePattern.matcher(value).find(0)) {
            var hour = Integer.parseInt(value.split(":")[0]);
            var minutes = Integer.parseInt(value.split(":")[1]);
            return LocalTime.of(hour, minutes);
        } else
            throw new DateTimeParseException("Podano godzinę w złym formacie!", value, 0);
    }

    public static int getDayFromUser(Scanner scanner) {
        return Integer.parseInt(getValueFromUser(scanner, "Podaj dzień tygodnia jako numer: "));
    }

    public static Priority getPriorityFromUser(Scanner scanner) {
        var value = getValueFromUser(scanner, "Proszę podać priorytet spotkania (niski, średni, wysoki): ");
        var priorityPattern = Pattern.compile("^wysoki|średni|niski$");

        if (priorityPattern.matcher(value).find(0)) {
            switch (value) {
                case "niski" -> {
                    return Priority.LOW;
                }
                case "średni" -> {
                    return Priority.MEDIUM;
                }
                case "wysoki" -> {
                    return Priority.HIGH;
                }
            }
        } else
            throw new IllegalArgumentException();

        return null;
    }

    public static Status getStatusFromUser(Scanner scanner) {
        var value = getValueFromUser(scanner, "Proszę podać status zadania (planowane, potwierdzone, realizowane, zrealizowane): ");
        var priorityPattern = Pattern.compile("^planowane|potwierdzone|realizowane|zrealizowane$");

        if (priorityPattern.matcher(value).find(0)) {
            switch (value) {
                case "planowane" -> {
                    return Status.PLANNED;
                }
                case "potwierdzone" -> {
                    return Status.CONFIRMED;
                }
                case "realizowane" -> {
                    return Status.IN_REALIZATION;
                }
                case "zrealizowane" -> {
                    return Status.DONE;
                }
            }
        } else
            throw new IllegalArgumentException();

        return null;
    }


    public static void printMenu() {
        System.out.println();
        System.out.println("""
                Wybierz jedną z opcji:
                a) Dodaj nowe spotkanie
                b) Dodaj nowe zadanie
                c) Usuń wpis z dnia
                d) Wyświetl wszystkie spotkania danego dnia
                e) Wyświetl wszystkie zadania danego dnia
                f) Wyświetl wszystkie spotkania danego dnia o danym priorytecie
                g) Wyświetl wszystkie zadania danego dnia o danym statusie
                h) Wyświetl wszystkie spotkania danego dnia od danej godziny
                i) Wyświetl wszystkie spotkania danego dnia między podanymi godzinami
                j) Wyświetl wszystkie spotkania danego dnia o podanym priorytecie po danej godzinie
                k) Wyświetl wszystkie zadani danego dnia o podanym statusie kończące się przed daną godziną
                l) Wyjdź
                """);
    }

    public static void addMeeting(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var description = getValueFromUser(scanner, "Prosze podać opis spotkania: ");

        var startTime = getLocalTimeFromUser(scanner, "Proszę podać czas początkowy spotkania w formacie (hh:mm): ");
        var endTime = getLocalTimeFromUser(scanner, "Proszę podać czas końcowy spotkania w formacie (hh:mm): ");

        var priority = getPriorityFromUser(scanner);

        calendar.addEntry(day, description, startTime, endTime, priority);
    }

    public static void addTask(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var description = getValueFromUser(scanner, "Prosze podać opis zadania: ");

        var startTime = getLocalTimeFromUser(scanner, "Proszę podać czas początkowy spotkania w formacie (hh:mm): ");
        var endTime = getLocalTimeFromUser(scanner, "Proszę podać czas końcowy spotkania w formacie (hh:mm): ");

        var status = getStatusFromUser(scanner);

        calendar.addEntry(day, description, startTime, endTime, status);
    }

    public static void deleteEntry(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var index = Integer.parseInt(getValueFromUser(scanner, "Podaj indeks zadania/spotkania w danym dniu: "));

        calendar.removeMeeting(day, index);
    }

    public static void printMeetingsDuringADay(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);

        calendar.printMeetingsDuringADay(day);
    }

    public static void printMeetingsDuringADayWithPriority(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var priority = getPriorityFromUser(scanner);

        calendar.printMeetingsDuringADay(day, priority);
    }

    public static void printMeetingsDuringADayAfterTime(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var startTime = getLocalTimeFromUser(scanner, "Podaj czas: ");

        calendar.printMeetingsDuringADay(day, startTime);
    }

    public static void printMeetingsDuringADayAfterTimeWithPriority(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var startTime = getLocalTimeFromUser(scanner, "Podaj czas: ");
        var priority = getPriorityFromUser(scanner);

        calendar.printMeetingsDuringADay(day, priority, startTime);
    }

    public static void printMeetingsDuringADayInBetweenTime(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var startTime = getLocalTimeFromUser(scanner, "Podaj czas: ");
        var endTime = getLocalTimeFromUser(scanner, "Podaj czas: ");

        calendar.printMeetingsDuringADay(day, startTime, endTime);
    }

    public static void printTasksDuringADay(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        calendar.printTasksDuringADay(day);
    }

    public static void printTasksDuringADayWithStatus(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var status = getStatusFromUser(scanner);

        calendar.printTasksDuringADay(day, status);
    }

    public static void printTasksDuringADayWithStatusBeforeEndTime(Scanner scanner, Calendar calendar) {
        var day = getDayFromUser(scanner);
        var status = getStatusFromUser(scanner);
        var endTime = getLocalTimeFromUser(scanner, "Proszę podać czas końcowy zadania w formacie (hh:mm): ");

        calendar.printTasksDuringADay(day, status, endTime);
    }
}
