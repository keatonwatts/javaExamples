/**
 * Author: Keaton Watts Last Modified: Feb 14, 2015
 * 
* This program uses UDP Server & Client Architecture to perform basic
 * operations. This .java file is the UDP server Client will ask server to
 * add doubles from and to a given amount. 
 */
//used http://javaprogramming.language-tutorial.com/2012/10/udp-client-server-communication-using.html for multiple references

package project2task3;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import static java.util.Arrays.*;

public class UDPServerWithDoubleArithmetic {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;

        //print
        System.out.println("java UDPServerWithDoubleArithmetic");

        try {
            aSocket = new DatagramSocket(6789);

            //create socket at agreed port
            //create arrays to store send and receive data
            byte[] receiveData = new byte[17];
            byte[] sendData = new byte[1000];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                aSocket.receive(receivePacket);

                //get data in byte array from client
                byte[] messageFromClient = receivePacket.getData();

                //doubles are stored in 8 bytes so...
                //capture the first set of 8 bytes for x
                byte[] xByte = new byte[8];
                xByte = copyOfRange(messageFromClient, 0, 8);

                //capture the second set of 8 bytes for y
                byte[] yByte = new byte[8];
                yByte = copyOfRange(messageFromClient, 8, 16);
                
                //capture the last byte for the operator
                byte[] opByte = new byte[1];
                opByte = copyOfRange(messageFromClient, 16, 17);
                
                //use conversion methods to converte byte array to long...
                //convert long to double and store in respective value
                double x = Double.longBitsToDouble(byteArrayToLong(xByte));
                double y = Double.longBitsToDouble(byteArrayToLong(yByte));

                //convert and store operation string
                String op = new String(opByte);

                //value to store answer in when computed in the switch below
                double answer = 0.0;

                //determine which operator was selected and execute appropriate method
                switch (op) {
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
                        answer = x / y;
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



    public static long byteArrayToLong(byte bytes[]) {
        long v = 0;
        for (int i = 0; i < bytes.length; i++) { // bytes[i] will be promoted to a long with the byte’s leftmost
// bit replicated. We need to clear that with 0xff.
            v = (v << 8) + (bytes[i] & 0xff);
        }
        return v;
    }

}
