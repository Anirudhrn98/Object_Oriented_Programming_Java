public class Runner implements Runnable {
    private int runnerID;

    public Runner() {
    }

    public Runner(int runnerID) {
        this.runnerID = runnerID;
    }

    public int getRunnerID() {
        return runnerID;
    }

    @Override
    public void run() {
        if (RelayRace.getRace() <= RelayRace.getTotalRaces()) {
            try {
                RelayRace.startRace(this);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
