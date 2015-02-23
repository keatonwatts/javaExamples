/**
* Author: Keaton Watts
* Last Modified: Jan 30, 2015
*
* This program performs various calculations on integers.
* Program will accept 2 numbers, x and y.
* Program will execute appropriate method depending upon
* the user's selection.
* Program will display results and allow another calculation.
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.math.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BigCalc", urlPatterns = {"/BigCalc"})

public class BigCalc extends HttpServlet {

    //palindromeCheck check = null;  // The "business model" for this app
    // @Override
    //public void init() {
    //    check = new palindromeCheck();
    // }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // store the number inputs from user
        String x = request.getParameter("numberX");
        String y = request.getParameter("numberY");

        // get the drop down (operator) parameter
        String operator = request.getParameter("operator");

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        String nextView;
        /*
         * Check if the search parameter is present.
         * If not, then give the user instructions and prompt for a search string.
         * If there is a search parameter, then do the search and return the result.
         */

        //if there are values then perform calculation
        if (x != null && y != null) {
            try {
                
                //store numbers in BigIntegers as specified
                BigInteger bigX = new BigInteger(x);
                BigInteger bigY = new BigInteger(y);
                
                //determine which operator was selected and execute appropriate method
                switch (operator) {
                    case "add":
                        //add x and y 
                        request.setAttribute("result", bigX.add(bigY).toString());
                        break;
                    case "multiply":
                        //multiple x and y
                        request.setAttribute("result", bigX.multiply(bigY).toString());
                        break;
                    case "relativelyPrime":
                        //determine if numbers are relatively prime
                        if (bigX.gcd(bigY).intValue() == 1) {
                            //x and y are relatively prime if condition is met
                            request.setAttribute("result", "relatively prime with GCD of " + bigX.gcd(bigY).toString());
                            break;
                        } else {
                            //x and y are not relatively prime
                            request.setAttribute("result", "not relatively prime with GCD of " + bigX.gcd(bigY).toString());
                            break;
                        }
                    case "mod":
                        try {
                            //x mod y
                            request.setAttribute("result", bigX.mod(bigY));
                            break;
                        } catch (ArithmeticException ae) {
                            request.setAttribute("result", "ERROR! Cannot calculate modulo due to input. Try again.");
                            break;
                        }
                    case "modInverse":
                        try {
                            //x inverse mod y
                            request.setAttribute("result", bigX.modInverse(bigY));
                            break;
                        } catch (ArithmeticException ae) {
                            request.setAttribute("result", "ERROR! Cannot calculate modular inverse due to input. Try again.");
                            break;
                        }
                    case "power":
                        try {
                            request.setAttribute("result", bigX.pow(bigY.intValue()));
                            break;
                        } catch (ArithmeticException ae) {
                            request.setAttribute("result", "ERROR! Cannot calculate due to input. Try again.");
                            break;
                        }
                }
            } 
            //this will handle invalid inputs
            catch (NumberFormatException nfe) {
                request.setAttribute("result", "ERROR! Cannot calculate due to input. Try again.");
            }
            //after executing if and switch then load results
            nextView = "result.jsp";

        } else {
            // no parameters
            nextView = "index.jsp";
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

}
