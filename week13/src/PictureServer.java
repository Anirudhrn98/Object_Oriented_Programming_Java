/*
 * This program downloads the images from another computer via tcp/ip sockets
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PictureServer {
    int port;
    ServerSocket serverSocket = null;
    PrintWriter outStream = null;
    BufferedReader inpStream = null;
    Socket socket = null;

    /**
     *  Sends data of the filePath to the user
     *
     * @param filePath string representing the filepath where the data is stored in the server
     */
    public void sendData(String filePath) {
        Path path = Paths.get(filePath);
        try {
            System.out.println(path.toAbsolutePath());
            Scanner sc = new Scanner(path.toAbsolutePath());
            while (sc.hasNextLine()) {
                outStream.println(sc.nextLine());
            }
            outStream.println("<EOF>");
        } catch (IOException e) {
            System.err.println(path + "doesn't exist. Make sure to input the correct filepath");
            e.printStackTrace();
        }
    }

    /**
     *  Received the filePath from client and sends the respective filecontent (line-by-line)
     *
     */
    public void receiveFilePath() throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listening on port " + serverSocket.getLocalPort());
            socket = serverSocket.accept();
            outStream = new PrintWriter(socket.getOutputStream(), true);
            inpStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String filePath = inpStream.readLine();
                if (filePath.equals("<EOT>"))
                    break;
                System.out.println("Filepath " + filePath);
                sendData(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null)
                outStream.close();
            if (inpStream != null)
                inpStream.close();
            if (socket != null)
                socket.close();
            if (serverSocket != null)
                serverSocket.close();
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

        try{
            port = Integer.parseInt(args[1]);
        } catch (Exception e){
            System.err.println("Make sure to input integer port in <port>");
            System.exit(-1);
        }

    }

    /**
     *  Main function
     */
    public static void main(String[] args) {
        PictureServer server = new PictureServer();
        server.parseArgs(args);
        try {
            server.receiveFilePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
