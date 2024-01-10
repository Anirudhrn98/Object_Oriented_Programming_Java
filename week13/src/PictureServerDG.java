/*
 * This program downloads the images from another computer via datagram sockets
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.io.IOException;
import java.net.*;
import java.nio.file.Paths;
import java.util.Scanner;


public class PictureServerDG {
    private int port;
    private static InetAddress ip;
    private static DatagramSocket ds = null;

    /**
     *  Sends data of the filePath to the user
     *
     * @param filePath string representing the filepath where the data is stored in the server
     */
    public void sendData(String filePath) {
        Scanner sc = null;
        try {
            byte[] data;
            System.out.println(Paths.get(filePath).toAbsolutePath());
            sc = new Scanner(Paths.get(filePath).toAbsolutePath());
            while (sc.hasNextLine()) {
                data = sc.nextLine().getBytes();
                DatagramPacket dpSend = new DatagramPacket(data, data.length, ip, port);
                ds.send(dpSend);
            }
            data = "<EOF>".getBytes();
            DatagramPacket dpSend = new DatagramPacket(data, data.length, ip, port);
            ds.send(dpSend);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sc != null)
                sc.close();
        }
    }

    /**
     *  Received the filePath from client and sends the respective filecontent (line-by-line)
     *
     */
    public void receivePathSendFile() throws IOException {
        ds = new DatagramSocket(port);
        try {
            while (true) {
                byte[] receive = new byte[65535];              // Clear the buffer after every message.
                DatagramPacket dpReceive = new DatagramPacket(receive, receive.length);
                System.out.println("Listening on port " + ds.getLocalPort());
                ds.receive(dpReceive);
                ip = dpReceive.getAddress();
                port = dpReceive.getPort();
                String filePath = new String(dpReceive.getData(), dpReceive.getOffset(), dpReceive.getLength());
                if (filePath.equals("<EOT>"))
                    break;
                System.out.println("Filepath " + filePath);
                sendData(filePath);
            }
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Checks the arguments from the user to make sure they are in the form
     *  that the program can further process them adequately
     */
    private void parseArgs(String[] args) {
        if ((args.length != 2) || (!args[0].equals("-port"))) {
            System.err.println("The PictureServer.java needs to have 1 (one) argument!");
            System.err.println("java PictureServer -port <port>");
            System.exit(-1);
        }

        try {
            port = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.err.println("Make sure to input integer port in <port>");
            System.exit(-1);
        }

    }

    /**
     *  Main function
     */
    public static void main(String[] args) {
        PictureServerDG server = new PictureServerDG();
        server.parseArgs(args);
        try {
            server.receivePathSendFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
