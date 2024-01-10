///*
// * Literals1.java
// *
// * Version:
// *      $Id$
// *
// * Revision:
// *      $Log$
// *
// */
//
///*
// * This program prints all the nearly perfect numbers, from 2  to 1000.
// *
// * @author      Anirudh Narayanan
// * @author      Bardh Rushiti
// */
//
//import java.util.Arrays;
//
//public class Water2 {
//    static int[] myVolumes = { 1, 1, 2, 4, 5, 6 };
//    static int soManyBuckets = myVolumes.length;
//    static int[][] combo = new int[soManyBuckets][soManyBuckets];
//    static int[][] dumbArray;
//    //    static int[] bucketsToFill = { 1, 2, 3, 4, 6, 7, 8, 9 };
//        static int targetBucket = 5;
//    static boolean largestAmountOfUsedBuckets = false;
//
//    // TODO: Do we need to define all varibles in the recursive func?
//
//    /**
//     * The main program.
//     *
//     * @param    args    command line arguments (ignored)
//     */
//
//    public static void filterSolution (int[][] combo, boolean largestAmountOfUsedBuckets){
//        int cntr;
//        int max = 0;
//        int maxId = 0;
//        int min = soManyBuckets;
//        int minId = 0;
//
//        System.out.println(Arrays.toString(combo));
//        for (int i=0; i < soManyBuckets; i++){
//            cntr = 0;
//            for (int j=0; j < soManyBuckets; j++) {
//                if (combo[i][j] > 0){
//                    cntr++;
//                }
//            }
//            if (max > cntr) {
//                max = cntr;
//                maxId = i;
//            }
//            if (min < cntr){
//                min = cntr;
//                minId = i;
//            }
//        }
//        System.out.println(min + " " + minId + " " +  max + " " + maxId);
//        if (largestAmountOfUsedBuckets){
//            for (int index = 0; index < soManyBuckets; index++){
//                if(combo[maxId][index] > 0){
//                    System.out.print(combo[maxId][index] + "L ");
//                }
//            }
//            System.out.println();
//        } else {
//            for (int index = 0; index < soManyBuckets; index++){
//                if(combo[minId][index] > 0){
//                    System.out.print(combo[minId][index] + "L ");
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    public static void filledBucket(int target, int sum, int[] myVolumes, int[][] combo,int firstId, int secId){
//        if (firstId == soManyBuckets && secId == soManyBuckets){
//            for (int j = 0; j < soManyBuckets; j++) {
//                for (int i = 0; i < soManyBuckets; i++) {
//                    if (combo[j][i] > 0){
//                        System.out.println(Arrays.toString(combo[j]));
//                        break;
//                    }
//                }
//            }
//        return combo;
//        }
//
//        // Filter unwated values that don't reach target
//        if ((secId == soManyBuckets)  && (sum != target)) {
//            for (int x=0; x < soManyBuckets; x ++){
//                combo[firstId][x] = 0;
//            }
//        }
//
//        if ((sum == target) || (secId == soManyBuckets)) {
//            sum = 0;
//            secId = firstId + 1;
//            firstId += 1;
//
//            filledBucket(target, sum, myVolumes, combo, firstId, secId);
//            return;
//        }
//
//        // Move forward
//        if ((sum < target) && (target - sum >= myVolumes[secId])){
//            sum += myVolumes[secId];
//            combo[firstId][secId] = myVolumes[secId];
//            secId += 1;
//
//            filledBucket(target, sum, myVolumes, combo, firstId, secId);
//            return;
//        }
//
//        if ((sum < target) && (target - sum < myVolumes[secId])){
//            sum -= myVolumes[secId - 1];
//            combo[firstId][secId - 1] = 0;
//
//            filledBucket(target, sum, myVolumes, combo, firstId, secId);
//            return;
//        }
//
//        System.out.println("No solution found!");
//        return;
//    }
//
//    public static void main ( String args[]) {
//        int firstId = 0;
//        int secId = 0;
//        int sum = 0;
//        filledBucket(targetBucket, sum, myVolumes, combo, firstId, secId);
//        filterSolution(combo, largestAmountOfUsedBuckets);
//    }
//}