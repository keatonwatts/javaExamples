/*
Fraction.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 3-1
Description: See how we can manipulate fractions with java! 
*/

public class Fraction {
int numerator;
int denominator;

Fraction() {	// numerator = denominator = 1
    numerator = 1;
    denominator = 1;
}

Fraction(int n, int d) {
    numerator = n;
    denominator = d;
}

// greatest common divisor:
int gcd(int a, int b) { 
if (b == 0)
return (a);
else
return (gcd(b, a % b));
}

public String toString() {
	int gcd = gcd(numerator, denominator); //found code to return fraction as string at: http://www.cs.berkeley.edu/~jrs/61bf98/labs/lab2/Fraction.java
	return (numerator/gcd +"/" + denominator/gcd); //found code on how to return fraction as string at: http://www.cs.berkeley.edu/~jrs/61bf98/labs/lab2/Fraction.java
}

String toDecimal() { //capture as doubles to store decimal places. found code which suggested this at http://www.cs.berkeley.edu/~jrs/61bf98/labs/lab2/Fraction.java
	double n = numerator;
	double d = denominator;
	double decimal = (n/d); 
	String decimalFraction = String.valueOf(decimal); //since method returns a string, convert double to string. found code at http://stackoverflow.com/questions/5766318/converting-double-to-string
	return decimalFraction;
}

Fraction add(Fraction f) { //found code on how to add fractions at: http://www.cs.berkeley.edu/~jrs/61bf98/labs/lab2/Fraction.java
    Fraction x = new Fraction((numerator * f.denominator) + (f.numerator * denominator), denominator * f.denominator);
    return x;
	
}
}
