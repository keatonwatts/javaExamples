/**
* Author: Keaton Watts
* Last Modified: Feb 15, 2015
*
* This program uses TC{ Server & Client Architecture to perform basic operations.
* This .java file is the TCP client
* Commander provides secret key to encrypt/decrypt information sent between client and server
* Waits for spies to input their id, password, and location coordinates
* Secret keys much match!
* Can accept requests from n number of users while the server is still running
* Once a spy updates their location it updates a .kml file which can be used to see spy's locations
*/

package project2task4;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPSpyUsingTEAandPasswords {
	public static void main (String args[]) {
		// arguments supply message and hostname

		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out =new DataOutputStream(s.getOutputStream());
                        
                        //create scanner object
                        Scanner scan = new Scanner(System.in);       

                        //print
                        System.out.println("java TCPSpyUsingTEAandPasswords");

                        //prompt Spy to enter symmetric key
                        System.out.println("Enter symmetric key for TEA (taking first sixteen bytes):");
                        String key = scan.nextLine();
                        byte[] keyByte = key.getBytes();
                        //create tea object to encrypt/decrypt using symmetic key
                        TEA tea = new TEA(keyByte);

                        //prompt Spy to enter ID, scan, assign to byte array, encrypt with tea and send to server  
                        System.out.println("Enter your ID:");
                        String id = scan.nextLine();
                        byte[] idByte = tea.encrypt(id.getBytes());
                        out.write(idByte);
                        
                        //prompt Spy to enter password, scan, assign to byte array, encrypt with tea and send to server                     
                        System.out.println("Enter your Password:");    
                        String pass = scan.nextLine();
                        byte[] passByte = tea.encrypt(pass.getBytes());
                        out.write(passByte);
                        
                        //prompt Spy to enter password, scan, assign to byte array, encrypt with tea and send to server                     
                        System.out.println("Enter your location:");    
                        String location = scan.nextLine();
                        byte[] locationByte = tea.encrypt(location.getBytes());
                        out.write(locationByte);                        

                        //read response from server and display
			String serverResponse = in.readUTF();	    
			System.out.println(serverResponse) ; 
                        
                        
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}
