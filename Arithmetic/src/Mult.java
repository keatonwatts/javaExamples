/*
Mult.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 4-1 - Test
Description: Create a arithmetic tree that automatically creates operators and constants. We then print and solve the equations.
*/

public class Mult extends Binop {
    public Mult(Node l, Node r) {
        super(l, r);
    }
    public double eval() {
        return lChild.eval() * rChild.eval();
    }
    public String toString(){
    	return "(" + lChild.toString() + " * " + rChild.toString() + ")";
    }
}
