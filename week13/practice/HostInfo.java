import java.net.*;
import java.io.*;
import java.util.*;

public class HostInfo {
    public static void main(String argv[]) {
        InetAddress ipAddr;
        try {
            ipAddr = InetAddress.getLocalHost();
            System.out.println("This is " + ipAddr);
            System.out.println("getCanonicalHostName(): " + ipAddr.getCanonicalHostName());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }
    }
}
