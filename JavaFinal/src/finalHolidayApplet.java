/*
finalHolidayApplet.java
Keaton Watts (kcwatts@andrew.cmu.edu)
Course: 95-712: Object-Oriented Programming in Java
Assignment: Final Exam - Coding Section
Description: Create an applet so when the user clicks 'Dear Professor' they get the message 'Happy Holidays!'
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finalHolidayApplet extends JApplet {
    String message = "Happy Holidays!";
	JButton b1 = new JButton("Dear Professor");
    class BL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, message, "WooHoo!", JOptionPane.OK_OPTION);
        }
    }
    
    BL aListener = new BL();
    public void init() {
        b1.addActionListener(aListener);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(b1);
    }
}
