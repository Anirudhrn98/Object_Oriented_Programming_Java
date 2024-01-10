/**
 * This program finds numbers with the property that if you sum their individual elements, squared, and recursively until eventually
 * comes back to number one or a repeating number.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 **/

import java.util.Locale;

public class Number extends Thread {
    private static final String RESET = "\u001B[0m";
    private static final String RED_BOLD = "\033[1;31m";    // RED
    private static int upperLimit;
    private static final int MAX_ITER = 1000;

    private String details = "";

    private static String type;
    private static String[] detailsArr;
    private static int[] numsWithProp;

    private int to;
    private int from;
    private int[] partNumsWithProp;
    private String[] partDetailsArr;

    /**
     * Number class default constructor
     */
    public Number() {
    }

    /**
     * Number class constructor
     * @param       from        range from which the calculation starts
     * @param       to          range at which the calculation ends
     */
    public Number(int from, int to) {
        this.to = to;
        this.from = from;

        System.out.println(from + " - " + to);
        this.partNumsWithProp = new int[to - from];
        this.partDetailsArr = new String[to - from];
    }

    /**
     * Separates the number into individual digits
     * @param       num           the number to separate
     */
    private int[] separateDigits(int num) {
        String strReprNum = "" + num;
        int[] digitsInNum = new int[strReprNum.length()];
        for (int index = 0; index < strReprNum.length(); index++) {
            digitsInNum[index] = Integer.parseInt("" + strReprNum.charAt(index));
        }
        return digitsInNum;
    }

    /**
     * Checks if the number has the required property
     * @param       num           the number to separate
     */
    private boolean hasNumProp(int num) {
        // Have we seen this number in the past?
        int result = 0;
        details = "";
        int[] digits = separateDigits(num);
        boolean[] usedNumber = new boolean[MAX_ITER];

        do {
            usedNumber[result] = true;

            result = 0;
            for (int digit : digits) {
                result += Math.pow(digit, 2);
                details += digit + "^2 + ";
            }

            details = details.substring(0, details.length() - 3);
            details += "=" + result + " |";

            digits = separateDigits(result);
        }
        while (!usedNumber[result] && result != 1);

        return result == 1;
    }

    /**
     * Checks if the values from `from` to `to` have the required property
     */
    private void checkForNumProp() {
        for (int val = from; val < to; val++) {
            if (type.equals("global")) {        // run global variable
                synchronized (numsWithProp) {
                    if (hasNumProp(val)) {
                        numsWithProp[val] = val;
                        detailsArr[val] = details;
                    }
                }
            } else {
                if (hasNumProp(val)) {          // run local variable
                    partNumsWithProp[val % partNumsWithProp.length] = val;
                    partDetailsArr[val % partDetailsArr.length] = details;
                }
            }
        }
    }

    @Override
    public void run() {
        checkForNumProp();
    }

    /**
     * Processes command-line arguments
     */
    public static int processArgs(String[] args) {
        System.out.println(args.length);
        if (args.length != 2){
            System.out.println(RED_BOLD + "java Number <numberOfThreads> <upperLimit> <global | local>" + RESET);
            System.exit(-1);
        }

        int numThreads = 0;
        try {
            numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("First argument must be an integer.");
            System.out.println(RED_BOLD + "java Number <numberOfThreads> <upperLimit> <global | local>" + RESET);
            System.exit(1);
        }

        try {
            upperLimit = Integer.parseInt(args[1]);
            detailsArr = new String[upperLimit];
            numsWithProp = new int[upperLimit];
        } catch (NumberFormatException e) {
            System.out.println("Second argument must be an integer ");
            System.out.println(RED_BOLD + "java Number <numberOfThreads> <upperLimit> <global | local>" + RESET);
            System.exit(1);
        }

        type = "";
        try {
            type = args[2].toLowerCase(Locale.ROOT);
            if ((!type.equals("local")) && (!type.equals("global"))) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Third argument must be either local or global");
            System.out.println(RED_BOLD + "java Number <numberOfThreads> <upperLimit> <global | local>" + RESET);
            System.exit(1);
        }
        return numThreads;
    }

    /**
     * This methods prints the results of the word done by the threads
     *
     * @param       threads           the number of threads previously defined
     */
    public static void printResults(Number[] threads) {
        if (type.equals("global")) {
            for (int index = 0; index < numsWithProp.length; index++) {
                if (numsWithProp[index] != 0)
                    System.out.println(numsWithProp[index] + ": " + detailsArr[index]);
            }
        } else {
            for (Number thread : threads) {
                for (int val = 0; val < thread.partNumsWithProp.length; val++) {
                    if (thread.partNumsWithProp[val] != 0)
                        System.out.println(thread.partNumsWithProp[val] + ": " + thread.partDetailsArr[val]);
                }
            }
        }
    }

    /**
     * Public main method
     */
    public static void main(String[] args) throws InterruptedException {
        int numThreads = processArgs(args);

        int batch = (int) upperLimit / numThreads;
        Number[] threads = new Number[numThreads];
        for (int threadId = 0; threadId < numThreads; threadId++) {
            // if it's not the last thread
            if (threadId < numThreads - 1) {
                threads[threadId] = new Number(threadId * batch, (threadId + 1) * batch);
            } else {
                // take the remainder of the work
                threads[threadId] = new Number(threadId * batch, upperLimit);
            }
            // start thread
            threads[threadId].start();
        }

        // wait for all the threads to finish their work
        for (Number thread : threads)
            thread.join();

        // print results
        printResults(threads);
    }
}