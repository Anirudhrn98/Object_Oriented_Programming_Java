/*
 * What is the output of this program?
 */
public class TT_1 extends Thread    {

   static Object aObject = new Object();
    
    public TT_1(Object aObject )	{
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
        TT_1 aTT_1 = new TT_1(new Object());
        TT_1 aTT_2 = new TT_1(new Object());
        aTT_1.start();
        aTT_2.start();
    }
}

