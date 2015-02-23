
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServerTCP {

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port we are using

            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);

            /*
             * Block waiting for a new connection request from a client.
             * When the request is received, "accept" it, and the rest
             * the tcp protocol handshake will then take place, making 
             * the socket ready for reading and writing.
             */
            clientSocket = listenSocket.accept();
            // If we get here, then we are now connected to a client.

            // Set up "in" to read from the client socket
            Scanner in;
            in = new Scanner(clientSocket.getInputStream());

            // Set up "out" to write to the client socket
            PrintWriter out;
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

            /* 
             * Forever,
             *   read a line from the socket
             *   print it to the console
             *   echo it (i.e. write it) back to the client
             */
            //extract file name first line of request
            String data = in.nextLine();
            int startfarm = data.indexOf("/");
            int endfarm = data.indexOf(" ", startfarm);
            String name = data.substring(startfarm + 1, endfarm);
            //System.out.println("Echoing: " + name);

            //pass file name to file object
            File f = new File(name);

            //if the file path is not found...
            if (!f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader("404.html"));
                //StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    out.println(line);
                    out.flush();
                }
            } 
            
            //if the file path is found...
            else {
                BufferedReader br = new BufferedReader(new FileReader(name));
                //StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    out.println(line);
                    out.flush();
                }
            }

            // Handle exceptions
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());

            // If quitting (typically by you sending quit signal) clean up sockets
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }

    }

}
