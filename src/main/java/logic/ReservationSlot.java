package logic;

public class ReservationSlot {

    private DAY day;
    private TimeSlot slot;

    public ReservationSlot() {}

    /**
     * A ReservationSlot object contains the day and time slot as part of a reservation.
     * @param day Enum Day
     * @param slot Enum TimeSlot
     */
    public ReservationSlot(DAY day, TimeSlot slot) {
        this.day = day;
        this.slot = slot;
    }

    public String toString() {
        return slot + " am " + day;
    }

    public void setDay(DAY day) {
        this.day = day;
    }

    public DAY getDay() {
        return this.day;
    }

    public TimeSlot getSlot() {
        return this.slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

}
