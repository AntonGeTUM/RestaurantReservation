package logic;

import java.util.Random;

public class Table {

    private int numberOfPeople;
    private int id;

    public Table() {}

    /**
     * A table object has an ID and a fixed number of people that can fit at that table.
     * @param id the id of the table (int)
     */
    public Table(int id) {
        Random random = new Random();
        this.numberOfPeople = random.nextInt(2, 6);
        this.id = id;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
