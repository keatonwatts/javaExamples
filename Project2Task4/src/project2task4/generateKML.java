/**
* Author: Keaton Watts
* Last Modified: Feb 15, 2015
*
* This program uses TC{ Server & Client Architecture to perform basic operations.
* This .java file generates the KML file when called
* Commander provides secret key to encrypt/decrypt information sent between client and server
* Waits for spies to input their id, password, and location coordinates
* Secret keys must match!
* Can accept requests from n number of users while the server is still running
* Once a spy updates their location it updates a .kml file which can be used to see spy's locations
*/

package project2task4;

import java.io.*;

public class generateKML {

    public generateKML(String loc1, String loc2, String loc3) throws FileNotFoundException, IOException {

    //for writing on mac http://www.westwind.com/reference/OS-X/paths.html        
    //use PrintWriter & FileWriter to write text to the file
    //http://stackoverflow.com/questions/5759925/printwriter-vs-filewriter-in-java
        
        PrintWriter out = new PrintWriter(new FileWriter("/Users/Keaton/Desktop/SecretAgents.kml"));
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<kml xmlns=\"http://earth.google.com/kml/2.2\"\n"
                + "><Document>\n"
                + "<Style id=\"style1\">\n"
                + "<IconStyle>\n"
                + "<Icon>\n"
                + "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>\n"
                + "</Icon>\n"
                + "</IconStyle>\n"
                + "</Style><Placemark>\n"
                + "<name>jamesb</name>\n"
                + "<description>Spy</description>\n"
                + "<styleUrl>#style1</styleUrl>\n"
                + "<Point> <coordinates>" + loc1 + "</coordinates> </Point>\n" //update with location for James
                + "</Placemark>\n"
                + "<Placemark>\n"
                + "<name>joem</name>\n"
                + "<description>Spy</description>\n"
                + "<styleUrl>#style1</styleUrl>\n"
                + "<Point> <coordinates>" + loc2 + "</coordinates> </Point>\n" //update with location for Joe
                + "</Placemark>\n"
                + "<Placemark>\n"
                + "16\n"
                + "<name>mikem</name>\n"
                + "<description>Spy</description>\n"
                + "<styleUrl>#style1</styleUrl>\n"
                + "<Point> <coordinates>" + loc3 + "</coordinates> </Point>\n" //update with location for Mike
                + "</Placemark> </Document> </kml>");

        out.flush();
        out.println();

    }
}
