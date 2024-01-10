/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 * nur ein objekt wird benutzt
 */
public class T_4 extends Thread    {
   
    static Object o;
    String info;

    public T_4(String info, Object o)    {
        this.info = info;
        this.o = o;
    }     

    public void run () {
        synchronized ( o ) { 
            System.err.println("--->" + info);
            try {
                    sleep(1000);
            }
            catch (  InterruptedException e ) {
                System.err.println("Interrupted!");
            }
            System.err.println("<---" + info);
        }
    }

    public static void main (String args []) {
        new T_4("1",new Object()).start();
        new T_4("2", new Object()).start();
    }
}
