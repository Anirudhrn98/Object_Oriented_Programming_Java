public class RelayRace1 {
    private static final String o = "RelayRace";
    private static int race = 1;
    private static final int totalRaces = 5;
    private static final int totalRunners = 5;


    protected static void startRace(Runner1 runner) throws InterruptedException {
        synchronized (o) {
            if (runner.getRunnerID() == totalRunners) {
                new Thread(new Runner1(1)).start();
                race += 1;
                if (race <= totalRaces)
                    System.err.println("New Race (#race = " + race + ")");
                o.notifyAll(); // Next runner ready

            } else {
                System.err.println("\tBaton is handed to runner " + runner.getRunnerID());
                Thread.sleep(250); // 'Run' for 1000 ms

                if (runner.getRunnerID() < totalRunners) {
                    new Thread(new Runner1(runner.getRunnerID() + 1)).start();
                }
                o.wait(); // it's not their turn, they have to wait

            }
        }
    }

    protected static int getRace() {
        return race;
    }

    protected static int getTotalRaces() {
        return totalRaces;
    }

    public static void main(String[] args) {
        // TODO: Handle args

        // Start Relay Race
        System.err.println("New Race (#race = " + race + ")");
        new Thread(new Runner1(1)).start();
    }
}
