import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

//This class implements a webscraper to grab the pages that are related to the error
//This would use a search engine, most likely Google, to find the links.
//--we could try this link: https://developers.google.com/custom-search/json-api/v1/overview
public class SolutionFinder{
    //Need a socket to connect to the internet
    Socket socket;

    //A list of all of the links to various websites
    List<URL> links;

    //Download the URL webpages to display
    //Returns a list of all the webpages in a string format
    String[] getPlainTextPages(){
        
        //Open each link and download the webpages into String
        for(int i = 0; i < links.size(); ++i){
            
        }

        return null;
    }

    //Save a list of webpages into base file
    void savePagesToFile(String file){
        
    }
}
