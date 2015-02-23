/**
* Author: Keaton Watts
* Last Modified: Jan 30, 2015
*
* This program will find your anime doppelganger.
* User will input various attributes of an anime character.
* Program will go out to www.animecharacterdatabase.com
* and search based on the criteria you provided.
* The program will return the thumbnail of the image if viewed on a mobile device
* otherwise the program will display the full size image. 
* User can then search for another character.
* If character is not found, then user will receive error page.
*/

package InterestingPicture;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * The following WebServlet annotation gives instructions to the web container.
 * It states that when the user browses to the URL path /InterestingPictureServlet
 * then the servlet with the name InterestingPictureServlet should be used.
 */
@WebServlet(name = "InterestingPictureServlet",
        urlPatterns = {"/getAnInterestingPicture"})
public class InterestingPictureServlet extends HttpServlet {

    InterestingPictureModel anime = null;  // The "business model" for this app

    @Override
    public void init() {
        anime = new InterestingPictureModel();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //capture parameters
        String eyeColor = request.getParameter("eye_color");
        String hairColor = request.getParameter("hair_color");        
        String hairLength = request.getParameter("hair_length");
        String age = request.getParameter("age");        
        
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
        if (eyeColor != null && hairColor != null && hairLength != null && age != null) {
            
            //set pictureURL and pictureName for result page
            request.setAttribute("pictureURL", anime.animeSearch(eyeColor, hairColor, hairLength, age, mobile));
            request.setAttribute("pictureName", anime.getPictureName());
            nextView = "result.jsp";
            
        } else {
            
            // no search parameter so choose the prompt view
            nextView = "prompt.jsp";
            
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}
