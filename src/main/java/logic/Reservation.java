package logic;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Reservation {

    private Table table;
    private ReservationSlot reservationSlot;
    private int weekNumber;

    public Reservation() {}

    /**A reservation object consists of a table, a reservation slot and a week number.
     * @param table the table id
     * @param reservationSlot a reservation slot object
     * @param week the week number
     */
    public Reservation(Table table, ReservationSlot reservationSlot, int week) {
        this.table = table;
        this.reservationSlot = reservationSlot;
        this.weekNumber = week;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String toString() {
        return "Reservierung für Tisch " + table.getId() + " " + reservationSlot;
    }

    public Table getTable() {
        return table;
    }

    public ReservationSlot getReservationSlot() {
        return reservationSlot;
    }

    public void setReservationSlot(ReservationSlot reservationSlot) {
        this.reservationSlot = reservationSlot;
    }

    // a Schedule contains a list of all available Reservations in a week
    // it creates a Reservation for every Table, DAY and TimeSlot

    public static class Schedule {

        private List<Reservation> schedule;
        private int weekNumber;

        public Schedule() {}

        /**
         * A schedule object contains a list of all available reservations in a week. The constructor creates reservations
         * for each table in the list of tables from a restaurant, for each time slot and for each day of the week.
         * @param table the list of the tables of a restaurant
         * @param week the week number as int
         */
        public Schedule (List<Table> table, int week) {
            this.weekNumber = week;
            this.schedule = new ArrayList<>();
            List<DAY> days = List.of(DAY.Montag, DAY.Dienstag , DAY.Mittwoch, DAY.Donnerstag, DAY.Freitag,
                    DAY.Samstag, DAY.Sonntag);
            List<TimeSlot> slots = List.of(TimeSlot.ELEVEN, TimeSlot.THIRTEEN,
                    TimeSlot.FIFTEEN,  TimeSlot.SEVENTEEN,  TimeSlot.NINETEEN,
                     TimeSlot.TWENTYONE);
            for (Table t: table) {
                for (DAY d : days) {
                    for (TimeSlot s : slots) {
                        schedule.add(new Reservation(t, new ReservationSlot(d, s), week));
                    }
                }
            }
        }

        /**
         * Determines the current week number.
         * @return the current week number
         */
        public static int currentWeek() {
            LocalDate date = LocalDate.now();
            return date.get(WeekFields.of(Locale.GERMANY).weekOfYear());
        }
        // calculates the current day of the week
        public static int currentDay() {
            LocalDate date = LocalDate.now();
            return date.get(WeekFields.of(Locale.GERMANY).dayOfWeek());
        }

        public int getWeekNumber() {
            return weekNumber;
        }

        public List<Reservation> getSchedule() {
            return schedule;
        }

        public void setSchedule(List<Reservation> schedule) {
            this.schedule = schedule;
        }
    }

}
