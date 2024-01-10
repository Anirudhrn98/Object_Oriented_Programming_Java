/*
 * This program finds the square root of number 2 utilizing the power of threads
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

class SqrtOf extends Thread {
    private static final String RESET = "\u001B[0m";
    private static final String RED_BOLD = "\033[1;31m";    // RED

    private BigDecimal result = BigDecimal.valueOf(1);
    private static final BigDecimal one = BigDecimal.valueOf(1);
    private static final BigDecimal two = BigDecimal.valueOf(2);
    private static final BigDecimal four = BigDecimal.valueOf(4);
    private static int numThreads = 0;

    private int from;
    private int to;

    /**
     * SqrtOf class default constructor
     */
    public SqrtOf() {
    }

    /**
     * SqrtOf class constructor
     * @param       from        range from which the calculation starts
     * @param       to          range at which the calculation ends
     */
    public SqrtOf(int from, int to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Method that calculates the required value using a synchronized block.
     * @param       from        range from which the calculation starts
     * @param       to          range at which the calculation ends
     */
    private void calculateSqrtOf3(int from, int to) {
            for (int k = from; k < to; k++) {
                BigDecimal temp = four.multiply(BigDecimal.valueOf(k)).add(two);
                result = result.multiply(one.subtract(one.divide(temp.pow(2), 10, RoundingMode.CEILING)));
            }
    }

    /**
     *  The run method for the threads
     **/
    @Override
    public void run() throws ArithmeticException {
        calculateSqrtOf3(this.from, this.to);
    }

    /**
     * THe method that processes the input arguments
     * @param       args            command line arguments
     * @return      upperLimmit     The number of calculations to be performed
     */
    private static int processArgs(String[] args) {
        if (args.length != 2){
            System.out.println(RED_BOLD + "java SqrtOf <numberOfThreads> <upperLimit>" + RESET);
            System.exit(-1);
        }

        try {
            numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("First argument must be an integer.");
            System.out.println(RED_BOLD + "java SqrtOf <numberOfThreads> <upperLimit>" + RESET);

            System.exit(1);
        }

        int upperLimmit = 1;
        try {
            upperLimmit = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Second argument must be an integer.");
            System.out.println(RED_BOLD + "java SqrtOf <numberOfThreads> <upperLimit>" + RESET);

            System.exit(1);
        }

        return upperLimmit;
    }

    /**
     * The main method that starts all the threads and the prints out the output when all threads finish
     * @param   args        command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // Process string arguments
        int upperLimmit = processArgs(args);
        SqrtOf[] threads = new SqrtOf[numThreads];
        BigDecimal finalResult = BigDecimal.valueOf(1);

        // separates the workload in batches
        int batch = upperLimmit / numThreads;
        for (int threadId = 0; threadId < numThreads; threadId++) {
            if (threadId == numThreads - 1) {
                threads[threadId] = new SqrtOf(threadId * batch, upperLimmit);
            } else {
                threads[threadId] = new SqrtOf(threadId * batch, (threadId + 1) * batch);
            }
            threads[threadId].start();
        }

        for (SqrtOf thread : threads)
            thread.join();

        for (SqrtOf thread : threads) {
            finalResult = finalResult.multiply(thread.result);
        }

        System.out.printf("Result: %.40f \n", finalResult);
    }
}
