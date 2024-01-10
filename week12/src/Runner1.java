public class Runner1 implements Runnable {
    private int runnerID;
    private static int isRunning = 0;

    public Runner1() {
    }

    public Runner1(int runnerID) {
        this.runnerID = runnerID;
        isRunning = runnerID;
    }

    public static int getIsRunning() {
        return isRunning;
    }

    public int getRunnerID() {
        return runnerID;
    }

    @Override
    public void run() {
        if (RelayRace1.getRace() <= RelayRace1.getTotalRaces()) {
            try {
                RelayRace1.startRace(this);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
