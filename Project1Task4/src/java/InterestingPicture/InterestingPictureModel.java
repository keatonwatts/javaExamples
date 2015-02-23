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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Random;

public class InterestingPictureModel {

    private String pictureName;
    private String pictureURL;

    private String eyeColor;
    private String hairColor;
    private String hairLength;
    private String age;
    private boolean mobile;
    private String responseType;

    //accept the variables user input and also check if mobile browser
    public String animeSearch(String eye, String hair, String length, String a, Boolean mob) {

        eyeColor = eye;
        hairColor = hair;
        hairLength = length;
        age = a;
        mobile = mob;

        String response = "";
        try {
            // Create a URL for the desired page            
            URL url = new URL("http://www.animecharactersdatabase.com/sresults.php?gender=0&eye_color=" + eyeColor
                    + "&hair_color=" + hairColor + "&hair_length=" + hairLength + "&age=" + age + "&mimikko=0&otherchar=0");
            // Create an HttpURLConnection.  This is useful for setting headers.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            // Do something reasonable.  This is left for students to do.
        }

        //if the search returns no characters, provide user with error
        if (response.contains("<H3>0 Characters")) {
            return pictureName = "Error, no photo found!";
        } 
        
        //if the search did find  matching result(s)
        else {
            //if a mobile device is being used
            if (mobile == true) {
                //find where the thumbnail pictures begin
                int innerInitialScrape = response.indexOf("DIV id=tile1");
                //find the link where the image is stored
                int startScrape = response.indexOf("SRC=\"http://ami.animecharactersdatabase.com", innerInitialScrape);
                int endScrape = response.indexOf("\"", startScrape + 5);
                //capture the name of the character
                pictureName(response);
                //return thumbnail piture URL to be displayed on result page  
                
                //new
                pictureURL = response.substring(startScrape, endScrape + 1); 
                                System.out.println(pictureURL);
                //end new
                return pictureURL = response.substring(startScrape, endScrape + 1); 

            } 
            
            //if a desktop is being used
            else {
                //find and store the ID of the character in order to capture the large image URL
                int startScrape = response.indexOf("A HREF=\"character.php?id=");
                int endScrape = response.indexOf("\"", startScrape + 8);
                String characterURL = response.substring(startScrape + 8, endScrape);

                //create a new connection to the character's page
                String responseDesktop = "";
                try {
                    // Create a URL for the desired page by appending the scraped character URL    
                    URL url = new URL("http://www.animecharactersdatabase.com/" + characterURL);
                    // Create an HttpURLConnection.  This is useful for setting headers.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // Read all the text returned by the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String str;
                    while ((str = in.readLine()) != null) {
                        // str is one line of text readLine() strips newline characters
                        responseDesktop += str;
                    }
                    in.close();
                } catch (IOException e) {
                    // Do something reasonable.  This is left for students to do.
                }

                //capture the full size image URL
                int innerInitialScrape = responseDesktop.indexOf("A name=details");
                int innerScrape = responseDesktop.indexOf("SRC=\"http://ami.animecharactersdatabase.com/", innerInitialScrape);
                int innerEndScrape = responseDesktop.indexOf("\"", innerScrape + 5);
                
                //capture the name of the character
                pictureName(responseDesktop);

                //return the full sized image url
                return pictureURL = responseDesktop.substring(innerScrape, innerEndScrape + 1);

            }
        }

    }

    //this method will use the html response to find the name of the character
    public String pictureName(String mobileOrDesktopResponse) {
        responseType = mobileOrDesktopResponse;
        int nameScrapeStart;
        int nameScrapeEnd;

        //if a mobile browser is being used
        if (mobile == true) {
            //pull the name from the ALT tag in the image
            nameScrapeStart = responseType.indexOf("Image of ");
            nameScrapeEnd = responseType.indexOf("\"", nameScrapeStart + 9);
            return pictureName = "Your anime doppelganger is named " + responseType.substring(nameScrapeStart + 9, nameScrapeEnd).trim();
        } 
        //if a desktop browswer is being used
        else {
            //find the photo in the title from the anime character's main page
            nameScrapeStart = responseType.indexOf("<TITLE>");
            nameScrapeEnd = responseType.indexOf(" |", nameScrapeStart + 7);
            return pictureName = "Your anime doppelganger is named " +  responseType.substring(nameScrapeStart + 7, nameScrapeEnd).trim();

        }
    }

    //return pictureName
    public String getPictureName() {
        return pictureName;
    }

}


