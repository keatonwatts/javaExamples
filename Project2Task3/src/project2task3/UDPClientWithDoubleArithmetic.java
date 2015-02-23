/**
 * Author: Keaton Watts Last Modified: Feb 14, 2015
 * 
* This program uses UDP Server & Client Architecture to perform basic
 * operations. This .java file is the UDP client with proxy client will use server
 * to compute the sum of 1.25 + 1.25 + ... + 100.25 and print the result
 */
//used http://javaprogramming.language-tutorial.com/2012/10/udp-client-server-communication-using.html for multiple references

package project2task3;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

public class UDPClientWithDoubleArithmetic {

    public static void main(String args[]) throws IOException {

        //print
        System.out.println("java UDPClientWithDoubleArithmetic");
        System.out.println("Will compute the sum of 1.25 + 2.25 + ... + 100.25");
        
        double total = 0.0;
        //loop through from 1.25 to 100.25 and add each number sequentially
        for (double i = 1.25; i <= 100.25; i++) {
            //send numbers to add method
            total = add(total, i);

            //if i is not the last number, then print "i +"
            if (i != 100.25) {
                System.out.print(i + " + ");
            } //otherwise don't print plus
            else {
                System.out.print(i);
            }
        }
        //print total
        System.out.println(" = " + total);
        System.out.println("Total: " + total);

    }//end of main

    /**
     *
     * @param firstNum
     * @param secondNum
     * @return
     */
    public static double add(double firstNum, double secondNum) throws IOException {


        //capture numbers and operations and convert into byte arrays
        String operationString = "+";
        byte[] x = toByteArray(firstNum);
        byte[] y = toByteArray(secondNum);
        byte[] op = operationString.getBytes();

        //concatenate byte arrays into one total array to send to server
        //http://stackoverflow.com/questions/5513152/easy-way-to-concatenate-two-byte-arrays
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(x);
        outputStream.write(y);
        outputStream.write(op);
        
        //to send to server
        byte[] total = outputStream.toByteArray();

        
        String returnedMessage = null;
        // args give message contents and destination hostname
        DatagramSocket aSocket = null;
        // create arrays to recieve data

        byte[] receiveData = new byte[1000];

        try {
            aSocket = new DatagramSocket();

            //convert data into form to send to server
            //sendData = sendToServer.getBytes();

            //use getLocalHost() method and assign port
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;

            //create packet to send to server and send
            DatagramPacket sendPacket
                    = new DatagramPacket(total, total.length, aHost, serverPort);
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
            return Double.parseDouble(returnedMessage);
        }

    }

    //to convert doubles into byte array
    //http://stackoverflow.com/questions/2905556/how-can-i-convert-a-byte-array-into-a-double-and-back
    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }


}
