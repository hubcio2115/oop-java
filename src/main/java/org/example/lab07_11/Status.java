package org.example.lab07_11;

public enum Status {
    PLANNED("planowane"),
    CONFIRMED("potwierdzone"),
    IN_REALIZATION("realizowane"),
    DONE("zrealizowane");

    public final String stringValue;

    Status(String status) {
        this.stringValue = status;
    }
}
