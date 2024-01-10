/*
 * What is the output of this program?
 */
public class TT_4 extends Thread    {

   static Object aObject = new Object();
   static boolean createdSecond = false;
    
    public TT_4(Object aObject )	{
	this.aObject = aObject;
    }
    public void run () {
	synchronized ( aObject ) {
	       if ( ! createdSecond )	{
			TT_4 aaTT_4 = new TT_4(new Object());
			aaTT_4.start();
			createdSecond = true;
	       }
	       System.err.println("--> ");
	       try {
		      sleep(1000);
	       }
	       catch (  InterruptedException e ) {
			 System.err.println("Interrupted!");
	       }
	       System.err.println("<-- ");
       }
    }

    public static void main (String args []) {
        TT_4 aTT_4 = new TT_4(new Object());
        aTT_4.start();
    }
}

