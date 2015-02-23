import java.io.*;
import javax.net.ssl.*;
import java.net.*;
import javax.net.*;
import java.util.*;

public class Client {
    
    public static void main(String args[]) {
        
        int port = 6502;
        try {
            // tell the system who we trust
            System.setProperty("javax.net.ssl.trustStore","myCool.truststore");
            // get an SSLSocketFactory
            SocketFactory sf = SSLSocketFactory.getDefault();
            
            // an SSLSocket "is a" Socket
            Socket s = sf.createSocket("localhost",6502);
            
            
            BufferedWriter out = new BufferedWriter(
                                                    new OutputStreamWriter(
                                                                           s.getOutputStream()));
            BufferedReader in = new 
            BufferedReader(
                           new InputStreamReader(
                                                 s.getInputStream()));
            out.write("Hello server\n");
            out.flush();
            String answer = in.readLine();               
            System.out.println(answer);     
            
            //what is username?
            String userQuestion = in.readLine();
            System.out.println(userQuestion);
            
            //create scanner to accept input
            Scanner scanner = new Scanner(System.in);
            
            //accept username
            String userName = scanner.nextLine();
            
            //send to server
            out.write(userName);
            out.newLine();
            out.flush();
            
            //what is password?
            String passQuestion = in.readLine();
            System.out.println(passQuestion);
            
            //acept password
            String pass = scanner.nextLine();
            
            //send to server
            out.write(pass);
            out.newLine();
            out.flush();
                
            //close
            out.close();
            in.close();
        }
        catch(Exception e) {
            System.out.println("Exception thrown " + e);
        }
    }
} 
