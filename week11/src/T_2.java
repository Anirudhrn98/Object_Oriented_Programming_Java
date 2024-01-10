/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 * es gibt nur ein info variable
 * haengt davon ab wann die Threads die K. durchlaufen sind
 */
public class T_2 extends Thread {
    static String info;

    public T_2(String info) {
        this.info = info;
    }

    public void run() {
        synchronized (info) {
            System.err.println("--> " + info);
            try {
                wait(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.err.println("<-- " + info);
        }
    }

    public static void main(String args[]) {
        String aString = "a";
        new T_2(aString).start();
        try{
            sleep(10);
        }catch (Exception e){}
        new T_2(new String("a2  ")).start();
    }
}
