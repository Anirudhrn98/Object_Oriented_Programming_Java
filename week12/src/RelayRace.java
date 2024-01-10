/*
 * This program simulates the relay race of r runners for n races.
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

public class RelayRace {
    private static final Object baton = "RelayRace";
    private static int race = 1;
    private static int totalRaces;
    private static int totalRunners;

    /**
     *  RelayRace class constructor
     */
    protected static void startRace(Runner runner) throws InterruptedException {
        synchronized (baton) {
            // If it is the last runner
            if (runner.getRunnerID() == totalRunners) {
                System.err.println("\tBaton is handed to runner " + runner.getRunnerID());
                new Thread(new Runner(1)).start();

                race += 1;
                if (race <= totalRaces)
                    System.err.println("New Race (#race = " + race + ")");
                baton.notifyAll(); // Next runner ready, the new race can begin

            } else {
                System.err.println("\tBaton is handed to runner " + runner.getRunnerID());
                Thread.sleep(500); // 'Run' for 500 ms

                if (runner.getRunnerID() < totalRunners) {
                    new Thread(new Runner(runner.getRunnerID() + 1)).start();
                }
                baton.wait(); // it's not their turn, they have to wait
            }
        }
    }

    /**
     * Returns number of races that each runner the team of runners have finished
     *
     * @return
     */
    protected static int getRace() {
        return race;
    }

    /**
     * Returns the total number of races.
     *
     * @return
     */
    protected static int getTotalRaces() {
        return totalRaces;
    }

    /**
     * Checks if the number has the required property
     *
     *  @param args arguments from the command line arguments
     */
    private static void processArgs(String[] args) {
        if (args.length != 2) {
            System.err.println("The RelayRace.java needs to have two arguments!");
            System.err.println("java RelayRace <totalRunners> <totalRaces>");
            System.exit(-1);
        }
        try {
            totalRunners = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("First argument must be an integer");
            System.err.println("java RelayRace <totalRunners> <totalRaces>");
        }
        try {
            totalRaces = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Second argument must be an integer");
            System.err.println("java RelayRace <totalRunners> <totalRaces>");
        }
    }


    /**
     * Public main method
     */
    public static void main(String[] args) {
        processArgs(args);

        // Start Relay Race
        System.err.println("New Race (#race = " + race + ")");
        new Thread(new Runner(1)).start();
    }
}
