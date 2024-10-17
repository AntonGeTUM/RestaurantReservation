package logic;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Enum for days of the week as part of a reservation slot.
 */
public enum DAY {
    Montag(1),
    Dienstag(2),
    Mittwoch(3),
    Donnerstag(4),
    Freitag(5),
    Samstag(6),
    Sonntag(7);

    private final int value;

    DAY(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
