/*
 * What is the output of this program?
 */
public class TT_2 extends Thread    {

   static Object aObject = new Object();
    
    public TT_2(Object aObject )	{
	this.aObject = aObject;
    }
    public synchronized void run () {
       System.err.println("--> ");
       try {
              sleep(1000);
       }
       catch (  InterruptedException e ) {
                 System.err.println("Interrupted!");
       }
       System.err.println("<-- ");
    }

    public static void main (String args []) {
        TT_2 aTT_2 = new TT_2(new Object());
        aTT_2.start();
        TT_2 aaTT_2 = new TT_2(new Object());
        aaTT_2.start();	// would aTT_2.start() work?
    }
}

