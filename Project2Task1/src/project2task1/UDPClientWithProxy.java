/**
* Author: Keaton Watts
* Last Modified: Feb 13, 2015
*
* This program uses UDP Server & Client Architecture to perform basic operations.
* This .java file is the UDP client with proxy
* Client will ask user to input a number (k)
* Program will add by sending value to server and display the value of the sum from 1 to k
*/

//used http://javaprogramming.language-tutorial.com/2012/10/udp-client-server-communication-using.html for multiple references
package project2task1;

import java.net.*;
import java.io.*;

public class UDPClientWithProxy {

    public static void main(String args[]) throws IOException {

        //print
        System.out.println("java UDPClientWithProxy");
        System.out.println("Enter an integer >= 1");

        //create BufferedReader to capture input from user
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
        String number = clientInput.readLine();

        //if number equals 1, there's nothing to add
        if (number.equals("1")) {
            System.out.println("1 = 1");
        } else {
            int total = 0;
            //loop through from 1 to n and add each number sequentially
            for(int i = 1; i <= Integer.parseInt(number); i++){
                //send numbers to add method
                total = add(total, i);
                
                //if i is not the number give, then print "i +"
                if(i != Integer.parseInt(number))
                    System.out.print(i + " + ");
                //other wise don't print plus
                else
                    System.out.print(i);
            }
            //print total
            System.out.println(" = " + total);
        }
    }//end of main

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int add(int x, int y) {

        //create message in a string. For example:  1 2 +
        String sendToServer = x + " " + y + " " + "+";
        
        String returnedMessage = null;
        // args give message contents and destination hostname
        DatagramSocket aSocket = null;
        // create arrays to store send and recieve data
        byte[] sendData = new byte[1000];
        byte[] receiveData = new byte[1000];

        try {
            aSocket = new DatagramSocket();

            //convert data into form to send to server
            sendData = sendToServer.getBytes();

            //use getLocalHost() method and assign port
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;

            //create packet to send to server and send
            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length, aHost, serverPort);
            aSocket.send(sendPacket);

            //receive reply from server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            aSocket.receive(receivePacket);

            //convert message into string remove nulls,
            //learned how to erase trailing nulls from https://community.oracle.com/thread/2142069
            returnedMessage = new String(receivePacket.getData()).replace("\u0000", "");

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
            
            //return "x + y" answer back to main
            return Integer.parseInt(returnedMessage);
        }

    }
}
