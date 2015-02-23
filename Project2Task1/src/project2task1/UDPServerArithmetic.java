/**
* Author: Keaton Watts
* Last Modified: Feb 13, 2015
*
* This program uses UDP Server & Client Architecture to perform basic operations.
* This .java file is the UDP server
* Client will ask server to perform simple integer arithmetic
* Program can add, subtract, multiply, divide and exponent integers
* Arguments are separated by spaces
*/

//used http://javaprogramming.language-tutorial.com/2012/10/udp-client-server-communication-using.html for multiple references

package project2task1;

import java.net.*;
import java.io.*;

public class UDPServerArithmetic {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        
        //print
        System.out.println("java UDPServer");
        
        try {
            aSocket = new DatagramSocket(6789);
            
            //create socket at agreed port
            
            //create arrays to store send and receive data
            byte[] receiveData = new byte[1000];
            byte[] sendData = new byte[1000];
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                aSocket.receive(receivePacket);
                
                //make message into string remove nulls, and print the request received from client
                //learned how to erase trailing nulls from https://community.oracle.com/thread/2142069
                String messageFromClient = new String(receivePacket.getData()).replace("\u0000", "");
                System.out.println("Received request for " + messageFromClient);
                
                //split string into a string array 
                //http://stackoverflow.com/questions/6086381/split-string-into-an-array-of-string
                String[] splitMessage = messageFromClient.split(" ");

                //assigns first array value [0] into x and second array value [1] into y
                //this will allow us to perform operations
                //http://stackoverflow.com/questions/8348591/splitting-string-and-put-it-on-int-array
                int x = Integer.parseInt(splitMessage[0]);
                int y = Integer.parseInt(splitMessage[1]);
                
                //value to store answer in when computed in the switch below
                int answer = 0;
                
                //determine which operator was selected and execute appropriate method
                switch (splitMessage[2]) {
                    case "+":
                        //addition
                        //splitMessage[0] + splitMessage[1] 
                        answer = x + y;
                        break;
                    case "-":
                        //subtraction
                        //splitMessage[0] - splitMessage[1]
                        answer = x - y;
                        break;
                    case "/":
                        //division
                        //splitMessage[0] / splitMessage[1]
                        answer = x/y;
                        break;
                    case "^":
                        //exponent
                        //splitMessage[0] ^ splitMessage[1]
                        //https://au.answers.yahoo.com/question/index?qid=20080407131719AA4GjMU
                        answer = (int) Math.pow(x, y);
                        break;
                    case "X":
                        //multiplication
                        //splitMessage[0] * splitMessage[1]
                        answer = x * y;
                        break;
                    default:
                        break;
                }                
                
                //convert data to send back answer
                String finalData = String.valueOf(answer);
                sendData = finalData.getBytes();
                
                //send data back to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                aSocket.send(sendPacket);

            }//end while
            
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
