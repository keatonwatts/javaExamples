/*
TestArithmetic.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 4-1
Description: Create a arithmetic tree that automatically creates operators and constants. We then print and solve the equations.
*/

import java.util.Random;

public class TestArithmetic {
	
	//create random number generator for randomly selecting a constant value
    static Random r = new Random();
    
    //create empty node null for chooseOperator
    static Node n = null;
    
    public static void main(String[] args) {
         
    	//create nodes with random operators and random constants
    	//start with the bottom of the tree and go up
        for(int i = 0; i <5; i++){
        Node n1 = new Const(randomConstant());
        Node n2 = new Const(randomConstant());
        Node n3 = chooseOperator(n1,n2);
        Node n4 = new Const(randomConstant());
        Node n5 = new Const(randomConstant());
        Node n6 = chooseOperator(n4,n5);
        Node n7 = chooseOperator(n3,n6);
        System.out.println(n7.toString() + " = " + n7.eval());
        		}
        
    }  
    
    static public Node chooseOperator(Node a, Node b) {
    	 
        String array[] = {"+", "-", "*","/"};
 
        int random = 0;
        random = r.nextInt(4);
        if (array[random].equals("+")) {
            Node n = new Plus(a, b);
            return n;
        } else if (array[random].equals("-")) {
            Node n = new Minus(a, b);
            return n;
        } else if (array[random].equals("*")) {
 
            Node n = new Mult(a, b);
            return n;
        } else if (array[random].equals("/")) {
            Node n = new Divide(a, b);
            return n;
        }
 
        return n;
 
   }
    
    //randomly generate a constant number between 1-20 which can be passed to the operator classes
    public static int randomConstant(){
    	return r.nextInt(20)+1;
    }   
    
    
    
}