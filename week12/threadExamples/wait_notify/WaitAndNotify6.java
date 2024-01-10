/*
 * Should print out 0 1 0 1 0 1 ...
 *
 *
 */
public class WaitAndNotify6 extends Thread	{
    private String info;
    static Object o = new Object();
    static boolean oneIsRunning = false; // is static important?
    // es wird nur ein
    // Objekt erzeugt
    public WaitAndNotify6(String info) {
        this.info    = info;
    }
    public void run () {
        while ( true )	{
            synchronized ( o ) {
                o.notify();
                System.out.println(info);
                try {
                    if ( ! oneIsRunning )	{
                        ( new WaitAndNotify6("1") ).start();
                        oneIsRunning = true;
                    }
                    sleep(300);
                    o.wait();
                } catch ( Exception e ) { }
            }
        }
    }
    public static void main (String args []) {
        new WaitAndNotify6("0").start();
    }
}
