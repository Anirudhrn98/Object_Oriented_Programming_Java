import java.rmi.*;

public class HelloServer {

        public static void main(String args[])
        {
            try {
                    HelloInterface obj = new HelloImplementationSleep();
                    Naming.rebind("//localhost/HelloServer3", obj);
                    // Naming.rebind("//129.21.36.56/HelloServer", obj);
                    System.out.println("HelloServer bound in registry");
                } catch (Exception e) {
                    System.out.println("HelloImpl err: " + e.getMessage());
                    e.printStackTrace();
		    System.exit(0);
                }
        }
}
