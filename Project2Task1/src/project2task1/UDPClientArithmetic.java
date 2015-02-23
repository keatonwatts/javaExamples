/**
* Author: Keaton Watts
* Last Modified: Feb 13, 2015
*
* This program uses UDP Server & Client Architecture to perform basic operations.
* This .java file is the UDP client
* Client will take 3 values from user: number, number, math symbol
* Client will ask server to perform simple integer arithmetic
* Program can add, subtract, multiply, divide and exponent integers
* Arguments are separated by spaces
*/

//used http://javaprogramming.language-tutorial.com/2012/10/udp-client-server-communication-using.html for multiple references

package project2task1;

import java.net.*;
import java.io.*;

public class UDPClientArithmetic {

    public static void main(String args[]) {
        // args give message contents and destination hostname
        DatagramSocket aSocket = null;
        
        //print
        System.out.println("java UDPClient");
        
        //create arrays to store send and recieve data
        byte[] sendData = new byte[1000];
        byte[] receiveData = new byte[1000];
        
        try {
            aSocket = new DatagramSocket();
            
            //message
            System.out.println("Enter simple postfix expression to be computed by server:");
            
            //create BufferedReader to capture input from user
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
            String expression = clientInput.readLine();
            
            //convert data into form to send to server
            sendData = expression.getBytes();

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

            //convert message into string remove nulls, and print the response received from server
            //learned how to erase trailing nulls from https://community.oracle.com/thread/2142069
            System.out.println(expression + " = " + new String(receivePacket.getData()).replace("\u0000", ""));
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
