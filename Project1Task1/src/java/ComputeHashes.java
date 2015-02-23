/**
* Author: Keaton Watts
* Last Modified: Jan 30, 2015
*
* This program converts a text string using hash function MD5 or SHA-1.
* Program will accept in a line of input.
* User can select MD5 or SHA-1.
* Upon submission, based on selection program will display Hex & Base64
* representations of the conversion.
* User can then enter another string to convert.
*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//imports for hash functions
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Keaton
 */
@WebServlet(name = "ComputeHashes", urlPatterns = {"/ComputeHashes"})

public class ComputeHashes extends HttpServlet {

    //palindromeCheck check = null;  // The "business model" for this app

   // @Override
    //public void init() {
    //    check = new palindromeCheck();
   // }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get the search parameter if it exists
        String search = request.getParameter("searchWord");
        
        // to show word on result page
        request.setAttribute("originalWord", search);
        
        // get the radio button parameter
        String radio = request.getParameter("hashType");

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        //checks to see if mobile browser
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
        if (search != null) {
            //if the user selects MD5
            if (radio.equals("md5"))
                try {
                    //set attributes for md5 hex and base and execute methods
                    request.setAttribute("searchResultHex", "MD5 (Hex): " + md5(search));
                    request.setAttribute("searchResultBase", "MD5 (Base): " + baseMd5(search));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
            //if the user selects SHA-1
            else
                try {
                    //set attributes for sha-1 hex and base and execute methods
                    request.setAttribute("searchResultHex", "SHA-1 (Hex): " + sha1(search));
                    request.setAttribute("searchResultBase", "SHA-1 (Base): " + baseSha1(search));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }

            //after setting attributes by executing appropriate methods go to result page
            nextView = "result.jsp";
        } else {
            // no search parameter so choose the index view
            nextView = "index.jsp";
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
    
    //base64 method for MD5
    //simply changed MessageDigest.getinstance to MD5 from baseSha1 method below
    public String baseMd5 (String search) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(search.getBytes());
	byte[] digest = md.digest();
        
        //create BASE64Encoder object and encode and return the base 64 array
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(digest);
    }
    
    //base64 method for SHA-1
    //sources partial code from: http://muhdazmilug.blogspot.com/2011/12/string-to-sha1-hash-base64.html
    public String baseSha1 (String search) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
	md.update(search.getBytes());
	byte[] digest = md.digest();
        
        //create BASE64Encoder object and encode and return the base 64 array
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(digest);
    }
    
            
    //sourced code from: http://www.avajava.com/tutorials/lessons/how-do-i-generate-an-md5-digest-for-a-string.html
    public String md5(String search) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(search.getBytes());
	byte[] digest = md.digest();
	StringBuffer sb = new StringBuffer();
	for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();           
    }
    
    //used md5 method from above and simply changed getInstance arguement to SHA-1
    public String sha1(String search) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
	md.update(search.getBytes());
	byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
	for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
        
    }
    
}
