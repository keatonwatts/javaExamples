/*
Const.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 4-1 - Test
Description: Create a arithmetic tree that automatically creates operators and constants. We then print and solve the equations.
*/

public class Const extends Node {
    private double value;
    public Const(double d) { value = d; }
    public double eval() { return value; }
    public String toString() {
        return "" + value;
    }
}
