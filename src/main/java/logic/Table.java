package logic;

import java.util.Random;

public class Table {

    private int numberOfPeople;
    private int id;

    public Table() {}

    public Table(int id) {
        Random random = new Random();
        this.numberOfPeople = random.nextInt(1, 4) * 2;
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
