/*
 * is	 3		3
 *	 4		4
 *	 3		4
 *	 4		3
 * der zweite Thread wird nicht benutzt
 */
public class T_5 extends Thread {
    static String i = "2";
    static String theValue;

    T_5(String i) {
        this.i = i;
    }

    public void run() {
        if (this.i.equals("1"))
            theValue = "3";
        else
            theValue = "4";
    }

    public static void main(String args[]) {
        T_5 aT_5_a = new T_5("1");
        T_5 aT_5_b = new T_5("2");

        aT_5_a.start();
        aT_5_a.run();        // <<-- ! start

        System.out.println("aT_5.i = " + aT_5_b.theValue);
        System.out.println("aT_5.i = " + aT_5_a.theValue);
    }
}
