package impl1;

import java.rmi.*;

public class HelloC {
	public static void main(String args[] ) {
	String message = "";
	try {
		// impl1.HelloInterface obj = (impl1.HelloInterface)Naming.lookup("//spiegel.cs.rit.edu/impl1.HelloServer");
		HelloInterface obj = (HelloInterface)Naming.lookup("//localhost/impl1.HelloServer");

		message = obj.sayHello();
		System.out.println(message);

		message = obj.sayHello(3);
		System.out.println(message);

	} catch (Exception e) {
		System.out.println("impl1.HelloC exception: " +
		e.getMessage());
		e.printStackTrace();
	}
  }
}
