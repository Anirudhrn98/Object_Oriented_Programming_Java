/*
 * This program simulates the producer/ consumer dynamic using a java program
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.Vector;

class Consumer extends Thread {
    int id;
    final int SO_MANY;
    Storage thisStorage;

    /**
     * Consumer Constructor - Thread
     *
     * @param id          integer to keep track of thread creation
     * @param thisStorage the Storage object with which the Producer will interact with
     * */
    Consumer(int id, Storage thisStorage) {
        this.id = 1;
        SO_MANY = 1;
        this.thisStorage = thisStorage;
        setName("Consumer: " + id);
        System.out.println("C: " + id);
    }

    /**
     * Thread running function
     * */
    public void run() {
        while (thisStorage.getTransactions() < thisStorage.getTransactionMax()) {
            Vector aVector = thisStorage.consume();
        }
    }
}
