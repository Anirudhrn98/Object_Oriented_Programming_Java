/*
 * What is the output of this program?
 */
public class TT_3 extends Thread {

    static Object aObject = new Object();
    static boolean createdSecond = false;
    static int num;

    public TT_3(Object aObject, int num) {
        this.num = num;
        this.aObject = aObject;
    }

    public synchronized static void tempMethod() {
//        System.err.println(num + " is currently running.");
        if (!createdSecond) {
            TT_3 aaTT_3 = new TT_3(new Object(), 2);
            aaTT_3.start();
            createdSecond = true;
        }
        System.err.println("--> " + num);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.err.println("<-- " + num);
    }

    @Override
    public void run() {
        tempMethod();
    }

    public static void main(String args[]) {
        TT_3 aTT_3 = new TT_3(new Object(), 1);
        aTT_3.start();
    }
}

