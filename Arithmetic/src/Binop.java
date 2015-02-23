/*
Binop.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 4-1 - Test
Description: Create a arithmetic tree that automatically creates operators and constants. We then print and solve the equations.
*/

public class Binop extends Node {
    protected Node lChild, rChild;
    public Binop(Node l, Node r) {
    	lChild = l; rChild = r;
    }
}
