package impl1;

import java.rmi.*;

public class HelloServer {

    public static void main(String args[]) {
        try {
            HelloInterface obj = new HelloImplementation();
            HelloInterface obj2 = new HelloImplementation();
//                    impl1.HelloInterface obj3 = new HelloImplementationSleep();
            Naming.rebind("//localhost/impl1.HelloServer", obj);
            Naming.rebind("//localhost/HelloServer2", obj2);
//                    Naming.rebind("//localhost/HelloServer3", obj3);
            // Naming.rebind("//129.21.36.56/impl1.HelloServer", obj);
            System.out.println("impl1.HelloServer bound in registry");
        } catch (Exception e) {
            System.out.println("HelloImpl err: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
