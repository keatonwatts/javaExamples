/**
 * Author: Keaton Watts Last Modified: Feb 15, 2015
 * 
* This program uses TC{ Server & Client Architecture to perform basic
 * operations. This .java file is the TCP server Commander provides secret key
 * to encrypt/decrypt information sent between client and server Waits for spies
 * to input their id, password, and location coordinates Secret keys must match!
 * Can accept requests from n number of users while the server is still running
 * Once a spy updates their location it updates a .kml file which can be used to
 * see spy's locations
 */
package project2task4;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPSpyCommanderUsingTEAandPasswords {

    public static void main(String args[]) {

        try {
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);

            //create scanner object
            Scanner scan = new Scanner(System.in);

            //print
            System.out.println("java TCPSpyCommanderUsingTEAandPasswords");

            //prompt Spy Commander to enter symmetric key
            System.out.println("Enter symmetric key for TEA (taking first sixteen bytes):");
            String key = scan.nextLine();
            //create new tea based on symmetric key
            TEA tea = new TEA(key.getBytes());

            //print
            System.out.println("Waiting for spies to visit...");

            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, tea);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }

}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    //use tea
    TEA tea;
    //counter to count number of visitors
    static int counter = 0;

    //give all users the default location of HBH @ CMU
    //jamesb belongs to loc1
    //joem belongs to loc2
    //mikem belongs to loc3
    static String loc1, loc2, loc3 = "-79.945389,40.444216,0.00000";

    public Connection(Socket aClientSocket, TEA teaServer) {
        try {
            this.tea = teaServer;
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {

            //to count number of visitors
            counter++;

            //to convert byte array to string below this source was used:
            //http://stackoverflow.com/questions/88838/how-to-convert-strings-to-and-from-utf8-byte-arrays-in-java
            //capture user name, read, store in byte array and decrypt with tea and convert to string
            byte[] spyUser = new byte[100];
            in.read(spyUser);
            byte[] decodeUser = tea.decrypt(spyUser);
            String decodedUserString = new String(decodeUser, "UTF-8");

            //capture password, read, store in byte array and decrypt with tea and convert to string
            byte[] spyPass = new byte[100];
            in.read(spyPass);
            byte[] decodePass = tea.decrypt(spyPass);
            String decodedPassString = new String(decodePass, "UTF-8");

            //capture location, read, store in byte array and decrypt with tea and convert to string
            byte[] spyLocation = new byte[100];
            in.read(spyLocation);
            byte[] decodeLocation = tea.decrypt(spyLocation);
            String decodedLocationString = new String(decodeLocation, "UTF-8");

            //determine if the symmetric key matches
            //if data is properly formatted and standard ASCII, then the server & client symmetric keys match
            //http://stackoverflow.com/questions/3585053/in-java-is-it-possible-to-check-if-a-string-is-only-ascii
            //symmetric keys match
            if (decodedUserString.matches("\\A\\p{ASCII}*\\z")) {
                if (decodedUserString.equals("jamesb") && decodedPassString.equals("james")) {

                    //print to server and send message to client
                    System.out.println("Got visit " + counter + " from " + decodedUserString);
                    String thankYou = "Thank you. Your location was securely transmitted to Intelligence Headquarters.";
                    out.writeUTF(thankYou);

                    //update location
                    loc1 = decodedLocationString;
                    //generate the kml file using a new class, generateKml and pass the location coordinates
                    generateKML kml = new generateKML(loc1, loc2, loc3);

                } else if (decodedUserString.equals("joem") && decodedPassString.equals("joe")) {

                    //print to server and send message to client
                    System.out.println("Got visit " + counter + " from " + decodedUserString);
                    String thankYou = "Thank you. Your location was securely transmitted to Intelligence Headquarters.";
                    out.writeUTF(thankYou);

                    //update location
                    loc2 = decodedLocationString;
                    //generate the kml file using a new class, generateKml and pass the location coordinates
                    generateKML kml = new generateKML(loc1, loc2, loc3);

                } else if (decodedUserString.equals("mikem") && decodedPassString.equals("mike")) {

                    //print to server and send message to client
                    System.out.println("Got visit " + counter + " from " + decodedUserString);
                    String thankYou = "Thank you. Your location was securely transmitted to Intelligence Headquarters.";
                    out.writeUTF(thankYou);

                    //update location
                    loc2 = decodedLocationString;
                    //generate the kml file using a new class, generateKml and pass the location coordinates
                    generateKML kml = new generateKML(loc1, loc2, loc3);

                } else {
                    //print to server and send message to client
                    System.out.println("Got visit " + counter + " from " + decodedUserString + ". Illegal id/password attempt.");
                    String invalid = "Not a valid user-id or password.";
                    out.writeUTF(invalid);
                }

            }//end of outer if
            else {
                System.out.println("Got visit " + counter + " illegal symmetric key used.");
            }

            //old code below
            //determine if the symmetric key matches
            //if data is properly formatted and standard ASCII, then the server & client symmetric keys match
            //http://stackoverflow.com/questions/3585053/in-java-is-it-possible-to-check-if-a-string-is-only-ascii
            //symmetric keys match
            /*
             if(decodedUserString.matches("\\A\\p{ASCII}*\\z")){
             if((decodedUserString.equals("jamesb") && decodedPassString.equals("james")) || (decodedUserString.equals("joem") && decodedPassString.equals("joe")) || (decodedUserString.equals("mikem") && decodedPassString.equals("mike"))){
                            
             //print to server and send message to client
             System.out.println("Got visit " + counter + " from " + decodedUserString);                            
             String thankYou = "Thank you. Your location was securely transmitted to Intelligence Headquarters.";    
             out.writeUTF(thankYou);
             }
             else{
             //print to server and send message to client
             System.out.println("Got visit " + counter + " from " + decodedUserString + ". Illegal id/password attempt.");
             String invalid = "Not a valid user-id or password.";
             out.writeUTF(invalid);
             }
             //once password and username have been confirmed to match, update location accordingly
             //update user location
             if(decodedUserString.equals("jamesb")){
             loc1 = decodedLocationString;
             }
             else if(decodedUserString.equals("joem")){
             loc2 = decodedLocationString;
             }
             else if(decodedUserString.equals("mikem")){
             loc3 = decodedLocationString;
             }

             //generate the kml file using a new class, generateKml and pass the location coordinates
             generateKML kml = new generateKML(loc1, loc2, loc3);
                        
                        
             }//end of outer if

             //symmetric keys do not match
             else{
             System.out.println("Got visit " + counter + " illegal symmetric key used.");
             }                    
                    
                    
             */
            /*
            
                        
   
                    
             */
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/*close failed*/

            }
        }

    }

}
