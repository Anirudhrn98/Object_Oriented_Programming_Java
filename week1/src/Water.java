/*
 * Water.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This program finds the minimum and maximum number of buckets required to fill the empty bucket.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

public class Water {
    static int[] myVolumes = { 4, 5, 6, 1, 1, 2 };                  // Number of available buckets
    static int soManyBuckets = myVolumes.length;                    // Number of maximum buckets to use
    static int[] bucketsToFill = { 1, 2, 3, 4, 6, 7, 8, 9};         // Targets of empty buckets
    static boolean largestAmountOfUsedBuckets = true;               // Variable that alternates between minimum and
                                                                    //      maximum of bucket used

    /**
     * Implementation of Bubble Sort
     *
     * @param   arr     unsorted array
     *
     * @return  arr     sorted array
     */
    static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        for (int index_1 = 0; index_1 < length-1; index_1++) {
            for (int index_2 = 0; index_2 < length - index_1 - 1; index_2 ++) {
                if (arr[index_2] > arr[index_2 + 1]) {

                    // swap arr[index_2+1] and arr[index_2]
                    int temp = arr[index_2];
                    arr[index_2] = arr[index_2 + 1];
                    arr[index_2 + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * Filters the values of combinations
     *
     * @param   combinations                    combinations used up until this points
     * @param   counts                          the number of buckets used for each combination
     * @param   largestAmountOfUsedBuckets      determines whether to print the max or min # of buckets and combinations
     */
    static void filterCombinations(String[] combinations, int[] counts, boolean largestAmountOfUsedBuckets){
        int minId = 0;  // store the index of minimum value
        int max = 0;    // store the max value
        int maxId = 0;  // store the index of maximum value

        for (int i = 0; i<counts.length; i++){
            // stop counting after 'null' values
            if (counts[i] == 0){
                break;
            }

            // find maximum bucket value and index
            if (counts[i] >= max){
                max = counts[i];
                maxId = i;
            }
            minId = i;
        }

        // print min/ max value of buckets used
        if (largestAmountOfUsedBuckets){
            System.out.println("Max number of buckets:" + counts[maxId] + " = " + combinations[maxId].substring(1));
        } else {
            System.out.println("Min number of buckets:" + counts[minId] + " = " + combinations[minId].substring(1));
        }
    }

    /**
     * Finding the [next] place to insert a string
     *
     * @param   combinations    combinations used up until this points
     * @param   usedBucket      the values of used buckets
     *
     * @return  index           the position of used buckets in the combination
     */
    public static int insertInComb (String [] combinations, String usedBucket){
        for (int index=0; index < combinations.length; index++){
            if (combinations[index] == null){
                combinations[index] = usedBucket;
                return index;
            }
        }
        return -1;
    }

    /**
     * Create all possible combinations of available bucket to fill the empty bucket
     *
     * @param    buckets       available full buckets to fill in the empty bucket
     * @param    index
     * @param    waterLeft     water left in the target bucket
     * @param    combinations  combinations of smaller bucket to achieve the target
     * @param    usedB         storing the used combination of buckets
     * @param    counts        storing the number of used combination of buckets used
     */
    static void createCombinations(int[] buckets, int index, int waterLeft, String[] combinations, String usedB, int[] counts){
        // if the bucket is overfilled, skip
        if (waterLeft < 0){
            return;
        }

        // if the bucket is filled completely, store the combination used
        if (waterLeft == 0){
            int combinationI = insertInComb(combinations, usedB);
            counts[combinationI] = usedB.length() / 3; // Because we append 4 x L
            return;
        }

        // for all other cases, continue searching for combination of buckets
        for (int j=index; j < soManyBuckets; j++){
            createCombinations(buckets, j+1, waterLeft - buckets[j],
                    combinations, usedB+"+"+buckets[j]+"L", counts);

        }
    }

    /**
     * The main program.
     *
     * @param    args    command line arguments (commented)
     */
    public static void main(String[] args) {
        // potential use of command line arguments for inputting value
//        if (args.length != 0){
//            int waterLeft = Integer.parseInt(args[0]);
//        }

        // Sorted volumes
        int[] myVolumesSorted = bubbleSort(myVolumes);
        String[] combinations   = new String [100]; //
        int[] counts = new int[100];     //

        for(int i=0; i < bucketsToFill.length; i++){
            createCombinations(myVolumesSorted, 0, bucketsToFill[i], combinations, "", counts);
            System.out.print(bucketsToFill[i] + "L - ");
            filterCombinations(combinations, counts, largestAmountOfUsedBuckets);
        }
    }
}

