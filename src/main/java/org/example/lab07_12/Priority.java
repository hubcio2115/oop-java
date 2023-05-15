package org.example.lab07_12;

public enum Priority {
    LOW("niski"),
    MEDIUM("średni"),
    HIGH("wysoki");

    public final String stringValue;

    Priority(String priority) {
        this.stringValue = priority;
    }
}
