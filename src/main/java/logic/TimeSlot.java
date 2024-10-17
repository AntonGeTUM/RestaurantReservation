package logic;

/**
 * Enum for the possible time slots during a day.
 */
public enum TimeSlot {

    ELEVEN("11:00", "13:00"),
    //TWELVE("13:00", "13:00"),
    THIRTEEN("13:00", "15:00"),
    //FOURTEEN("15:00", "16:00"),
    FIFTEEN("15:00", "17:00"),
    //SIXTEEN("16:00", "18:00"),
    SEVENTEEN("17:00", "19:00"),
    //EIGHTEEN("18:00", "20:00"),
    NINETEEN("19:00", "21:00"),
    //TWENTY("20:00", "22:00"),
    TWENTYONE("21:00", "23:00"),
    ;

    private final String from;
    private final String until;

    TimeSlot(String from, String until) {
        this.from = from;
        this.until = until;
    }

    public int startHour() { return Integer.parseInt(getFrom().substring(0, 2)); }

    public String getFrom() {
        return from;
    }

    public String getUntil() {
        return until;
    }

    public String toString() {
        return "von " + from + " bis " + until;
    }

}
