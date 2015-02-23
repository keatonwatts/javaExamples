/*
typeSpeed.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 1-2
Description: See how fast you can type the given sentence in milliseconds
*/

import java.util.*;

public class typeSpeed {
	public static void main(String[] args) {
		//declare variables
		long beginTime;
		long endTime;
		String pressEnterStart;
		String sentence;
		//utilize scanner to capture typing
        Scanner keyboard = new Scanner(System.in);
		System.out.println("Your job is to type the sentence \"I type very quickly\" as fast as you can."); //found code to print quotes @ http://stackoverflow.com/questions/3844595/how-can-i-make-java-print-quotes-like-hello
		System.out.println("When you are ready, press enter, type the sentence, and press enter again.");
        pressEnterStart = keyboard.nextLine();
        if(pressEnterStart.equals("")){  //captures first enter
        	beginTime = System.currentTimeMillis(); //record start time
        	sentence = keyboard.nextLine();
        	if(sentence.equals("I type very quickly")){ //ensure sentence is correct
        		endTime = System.currentTimeMillis(); //record end time
        		System.out.println(endTime - beginTime); //print result
        	} else {
        		System.out.println("User input is incorrect. Please try again."); //print error if sentence is incorrect
        			}
        }
	}
}
