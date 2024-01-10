/**
 * Literals1.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/**
 * This program prints all the nearly perfect numbers, from 2  to 1000.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

public class Water3 {
    static int[] myVolumes = { 1, 1, 2, 4, 5, 6 };
    // static int[] myVolumes = { 6, 5,};
    static int soManyBuckets = myVolumes.length;
    //    static int[] bucketsToFill = { 1, 2, 3, 4, 6, 7, 8, 9 };
    static int targetBucket = 5;
    static boolean largestAmountOfUsedBuckets = true;

    // TODO: Do we need to define all varibles in the recursive func?

    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */

    public static int filledBucket(int target, int[] myVolumes, int[] usedBuckets,int index, boolean largestAmountOfUsedBuckets){
        if (target == 0){
            for (int i=0; i < soManyBuckets; i++){
                if (usedBuckets[i] > 0) {
                    System.out.print(usedBuckets[i] + "L ");
                }
            }
            System.out.println();
            return 0;
        }

        while (index < soManyBuckets) {
            if (target >= myVolumes[index] && usedBuckets[index] != -1){
                usedBuckets[index] = myVolumes[index];
                target -= myVolumes[index];
                myVolumes[index] = 0;

                index += 1;

                filledBucket(target, myVolumes, usedBuckets, index, largestAmountOfUsedBuckets);
                return 0;
            } else if (target < myVolumes[index] && usedBuckets[index] != -1 && !largestAmountOfUsedBuckets){
                index -= 1;

                while (usedBuckets[index] == -1){
                    index -= 1;
                }

                myVolumes[index] = usedBuckets[index];
                target += usedBuckets[index];
                usedBuckets[index] = -1;

                filledBucket(target, myVolumes, usedBuckets, index, largestAmountOfUsedBuckets);
                return 0;
            } else if (usedBuckets[index] == -1 || largestAmountOfUsedBuckets){
                index += 1;
                filledBucket(target, myVolumes, usedBuckets, index, largestAmountOfUsedBuckets);
                return 0;
            }

        }
        System.out.println("No solution found!");
        return -1;
    }

    public static void main ( String args[]) {
//        System.out.println("Hello world!");
        int target = 6;
        int[] usedBuckets = {0, 0, 0, 0, 0, 0, 0, 0};
        int index = 0;
        System.out.print(target + "L: \t ");
        filledBucket(target, myVolumes, usedBuckets, index, true);
    }
}