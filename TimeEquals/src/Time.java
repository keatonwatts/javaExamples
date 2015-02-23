/*
TestTime.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Homework 4-3
Description: Use this provided class and create an equals method that will be used in
TestTime.java to determine if two objects are equal.
*/


//code below provided in class
public class Time {
    int hour;
    int minute;
    int second;

    Time() { setTime(0, 0, 0); }
    Time(int h) { setTime(h, 0, 0); }
    Time(int h, int m) { setTime(h, m, 0); }
    Time(int h, int m, int s) { setTime(h, m, s); }

Time setTime(int h, int m, int s) {
    setHour(h);
    setMinute(m);
    setSecond(s);
    return this;
}

Time setHour(int h) {
    hour = (( h >= 0 && h < 24 ) ? h : 0 );
    return this;
}

Time setMinute(int m) {
    minute = (( m >= 0 && m < 60 ) ? m : 0 );
    return this;
}
Time setSecond(int s) {
    second = ((s >= 0 && s < 60 ) ? s : 0 );
    return this;
}

int getHour() { return hour; }
int getMinute() { return minute; }
int getSecond() { return second; }

public String toString() {
    return "" + (( hour == 12 || hour == 0 ) ? 12 : hour % 12 ) +
        ":" + ( minute < 10 ? "0" : "" ) + minute +
        ":" + ( second < 10 ? "0" : "" ) + second +
        ( hour < 12 ? " AM" : " PM" ) ;
}


//this code was provided in class, but was modified to fit this specific situation
public boolean equals(Time t){
    if (this == t) return true;
    if (t == null) return false;
    if (getClass() != t.getClass())
    	return false;
    Time other = (Time)t;
    return hour == other.hour
    		&& minute == other.minute
    		&& second == other.second;
}

}
