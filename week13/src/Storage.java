/*
 * This program simulates the producer/ consumer dynamic using a java program
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.Vector;
import java.util.ArrayList;

class Storage {

    private final int nMatches = 90;
    private final int nBoxes = 9;

    private int transactions = 0;
    private final int transactionMax = 3;

    private int soManyMatches = 0;          // counter, used for verification
    private int soManyBoxes = 0;            // counter, used for verification

    private ArrayList theStorageForMatches = new ArrayList(nMatches);
    private ArrayList theStorageForBoxes = new ArrayList(nBoxes);
    private Object sync = new Object();


    /**
     * This test is a sanity check for all transactions of producer/ client
     *
     * @author      Anirudh Narayanan
     * @author      Bardh Rushiti
     */
    void test() {
        System.out.println("Matches: " + soManyMatches + " - Boxes: " + soManyBoxes);
        if (soManyMatches > nMatches) {
            System.out.println("overflow " + soManyMatches);
            System.exit(0);

        } else if (soManyBoxes > nBoxes) {
            System.out.println("overflow " + soManyBoxes);
            System.exit(0);

        } else if (soManyMatches < 0) {
            System.out.println("underflow " + soManyMatches);
            System.exit(0);

        } else if (soManyBoxes < 0) {
            System.out.println("underflow " + soManyBoxes);
            System.exit(0);
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds matches to the storage
     *
     * @param addTheseItems adds the items in the vector
     */
    synchronized void addMatches(Vector addTheseItems) {
        if (soManyMatches + addTheseItems.size() < nMatches) {
            System.err.println("---> produce matches");
            for (int index = 0; index < addTheseItems.size(); index++) {
                theStorageForMatches.add(addTheseItems.elementAt(index));
            }
            soManyMatches += addTheseItems.size();
            test();
            System.err.println("<--- produce matches");
        }
    }

    /**
     * Adds boxes to the storage
     *
     * @param addTheseItems adds the items in the vector
     */
    synchronized void addBoxes(Vector addTheseItems) {
        if (soManyBoxes + addTheseItems.size() < nBoxes) {
            System.err.println("---> produce boxes");
            for (int index = 0; index < addTheseItems.size(); index++) {
                theStorageForBoxes.add(addTheseItems.elementAt(index));
            }
            soManyBoxes += addTheseItems.size();
            test();
            System.err.println("<--- produce boxes");
        }
    }

    /**
     * Consumes one product, 1 (one) match box and fifty (50) matches, per client
     *
     * */
    synchronized Vector consume() {
        if (transactions >= transactionMax)
            return null;

        Vector aVector = new Vector(0);

        if ((soManyMatches  >= 50) && (soManyBoxes  >= 1)) {
            transactions += 1;
            System.err.println("----> consume");

            aVector.add(theStorageForBoxes.remove(0));

            for (int index = 0; index <  50; index++) {
                aVector.add(theStorageForMatches.remove(0));
            }

            soManyMatches -= 50 ;
            soManyBoxes -= 1;
            test();
            System.err.println("<--- consume");
            return aVector;
        } else
            return null;
    }

    public int getTransactionMax() {
        return transactionMax;
    }

    public int getTransactions() {
        return transactions;
    }
}
