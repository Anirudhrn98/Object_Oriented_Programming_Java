import java.util.Vector;
class WaitAndNotify_0 extends Thread {
    private static int counter = 0;
    private String name = null;
    private static Vector aVector;

    public WaitAndNotify_0(String name, Vector aVector) {
        this.aVector = aVector;
        this.name = name;
    }
    public void run() {
        synchronized (aVector) {
            if (name.equals("two")) {
                System.out.println(name + " will wait ...");
                aVector.notify();
                System.out.println(name + " done.");
            } else {
                try {
                    System.out.println(name + " will wait ...");
                    aVector.wait();
                } catch (IllegalMonitorStateException e) {
                    System.out.println(": IllegalMonitorStateException");
                } catch (InterruptedException e) {
                    System.out.println(": InterruptedException");
                }
                System.out.println(name + " is awake!");
            }
        }
    }
    public static void main(String args[]) {
        Vector theVector = new Vector();
        new WaitAndNotify_0("two", theVector).start();
        try {sleep(100);}catch (Exception e){}
        new WaitAndNotify_0("one", theVector).start();
    }
}
