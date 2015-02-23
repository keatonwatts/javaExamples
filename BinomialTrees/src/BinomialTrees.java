/*
binomialTrees.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 3-2
Description: Create a Binomial Tree that holds stock prices.
*/

import java.text.DecimalFormat;
public class BinomialTrees {	
	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("#.00"); //decimal format & import above taken from http://stackoverflow.com/questions/20463196/java-always-keep-two-decimal-places-even-in-zeroes
		
		//create ragaged array size
		int size = 5;
		
		//create ragged array; code taken from course textbook pg. 104
		double[][] odds = new double[size +1][];
		for (int n = 0; n <= size; n++)
			odds[n] = new double[n + 1];
		
		//fill ragged array; code taken from course textbook pg. 104
		for (int n = 0; n < odds.length; n++)
			for(int k = 0; k < odds[n].length; k++)
			{			
				odds[n][k] = 50 * Math.pow(1.1224, 2*k-n);  //formula taken from BinomialTrees.ppt from class
			}
		
		//print ragged array; code taken from course textbook pg. 104
		for(double[] row : odds)
		{
			for (double odd: row)
				System.out.print(" " + df.format(odd));
			System.out.println();
		}
		
		}
	}

