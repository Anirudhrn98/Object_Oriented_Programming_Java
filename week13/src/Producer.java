/*
 * This program simulates the producer/ consumer dynamic using a java program
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.Vector;


/**
 * Dummy class to simulate match object creation
 * */
class Match{
    final String name = "Matches";
    final String brandName = "generic";
    final String headColor = "red";
}

/**
 * Dummy class to simulate box object creation
 * */
class Box{
    final String name = "Box";
    final String brandName = "Diamond";
}


class MatchProducer extends Thread {
    int id;
    final int SO_MANY;
    Storage thisStorage;

    /**
     * MatchProducer Constructor - Thread
     *
     * @param id          integer to keep track of thread creation
     * @param thisStorage the Storage object with which the Producer will interact with
     * */
    MatchProducer(int id, Storage thisStorage) {
        this.id = 1;
        this.thisStorage = thisStorage;
        SO_MANY = 50;
        setName("Match Producer: " + id);
        System.out.println("MP: " + id);
    }

    /**
     * Thread running function
     * */
    public void run() {
        while (thisStorage.getTransactions() < thisStorage.getTransactionMax()) {
            Vector aVector = new Vector();
            for (int counter = 0; counter < SO_MANY; counter++) {
                aVector.add(id + "_" + new Match());
            }
            thisStorage.addMatches(aVector);
        }
    }
}

class BoxProducer extends Thread {
    int id;
    final int SO_MANY;
    Storage thisStorage;

    /**
     * BoxProducer Constructor - Thread
     *
     * @param id          integer to keep track of thread creation
     * @param thisStorage the Storage object with which the Producer will interact with
     * */
    BoxProducer(int id, Storage thisStorage) {
        this.id = 1;
        SO_MANY = 1;
        this.thisStorage = thisStorage;
        setName("Box Producer: " + id);
        System.out.println("BP: " + id);
    }

    /**
     * Thread running function
     * */
    public void run() {
        while (thisStorage.getTransactions() < thisStorage.getTransactionMax()) {
            Vector aVector = new Vector();
            for (int counter = 0; counter < SO_MANY; counter++) {
                aVector.add(id + "_" + new Box());
            }
            thisStorage.addBoxes(aVector);
        }
    }
}
