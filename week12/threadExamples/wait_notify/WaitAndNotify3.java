import java.util.Vector;

class WaitAndNotify extends Thread {

    private String info;
    static String aString = new String("0");

    public WaitAndNotify(String info, String aString) throws InterruptedException {
        this.info = info;
        this.aString = new String(info);
    }

    public void run() {
        synchronized (aString) {
            System.out.println("Key is: " + aString);
            if (info.equals("first")) {
                try {
                    new WaitAndNotify("second", "a").start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (info.equals("second")) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(info + " is waking up ..." + aString);
                aString.notifyAll();
                System.out.println(info + " done.");
            } else {
                System.out.println(info + " is waiting..." + aString);
                try {
                    sleep(3000);
                    aString.wait(3000);
                } catch (InterruptedException e) {
                    System.out.println(info +
                            ": IllegalMonitorStateException");
                }
                System.out.println(info + " is awake!");
            }
        }
    }

    public static void main(String[] args) {
        try {
            new WaitAndNotify("first", aString).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
