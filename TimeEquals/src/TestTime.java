/*
TestTime.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 4-3
Description: Demonstrate how the equals() method in the Time.class checks for
reflexive, symmetric and transitive properties as described in lecture.
*/

public class TestTime {
    public static void main(String[] args) {
    	Time t1 = new Time();
    	Time t2 = new Time(20, 3, 45);
    	Time t3 = new Time(20, 3, 45); //create t3, which is the same as t2 to demonstrate symmetric and transitive properties
    	Time t4 = new Time(20, 3, 45); //create t4, which is the same as t2 and t3 to demonstrate transitive properties
    	
    	
        t1.setHour(7).setMinute(32).setSecond(23);
    	System.out.println("t1 is " + t1);
    	System.out.println("t2 is " + t2);
    	System.out.println("t3 is " + t3);
    	System.out.println("t4 is " + t4);
    	System.out.println();
    	
    	//this demonstrates REFLEXIVE properties. x.equals(x)
    	if (t1.equals(t1))
    		System.out.println("The times are equal. The time is: " + t1 + " for both objects.");
    	else {
    		System.out.println("The times are not equal.");
    	}
    	
    	//this demonstrates SYMMETRIC properties. if x.equals(y)...
    	if (t2.equals(t3))
    		System.out.println("The times are equal. The time is: " + t2 + " and " + t3 + " for both objects.");
    	else {
    		System.out.println("The times are not equal.");
    	}
    	
    	//this demonstrates SYMMETRIC properties. ...then y.equals(x)
    	if (t3.equals(t2))
    		System.out.println("The times are equal. The time is: " + t3 + " and " + t2 + " for both objects.");
    	else {
    		System.out.println("The times are not equal.");
    	}
    	
    	//this demonstrates TRANSITIVE properties. if x.equals(y)...
    	if (t2.equals(t3))
    		System.out.println("The times are equal. The time is: " + t2 + " and " + t3 + " for both objects.");
    	else {
    		System.out.println("The times are not equal.");
    	}
    	
    	//this demonstrates TRANSITIVE properties. ...and y.equals(z)...
    	if (t3.equals(t4))
    		System.out.println("The times are equal. The time is: " + t2 + " and " + t3 + " for both objects.");
    	else {
    		System.out.println("The times are not equal.");
    	}
    	
    	//this demonstrates TRANSITIVE properties. ...then x.equals(z)
    	if (t2.equals(t4))
    		System.out.println("The times are equal. The time is: " + t2 + " and " + t3 + " for both objects.");
    	else {
    		System.out.println("The times are not equal.");
    	}
    	
    	//this demonstrates how the method can determine if the objects are unequal in any attribute
    	if (t1.equals(t2))
    		System.out.println("The times are equal. The time is: " + t1 + " and " + t1 + " for both objects.");
    	else {
    		System.out.println("The times are not equal. The time is: " + t1 + " and " + t2 + " for both objects.");
    	}
    	
    }
}