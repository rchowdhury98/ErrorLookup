import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

//This class implements a webscraper to grab the pages that are related to the error
//This would use a search engine, most likely Google, to find the links.
//--we could try this link: https://developers.google.com/custom-search/json-api/v1/overview
public class SolutionFinder{

    //Default contructor
    public SolutionFinder(){
        links = new ArrayList<URL>();
    }

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

//Class used to search on StackOverflow and then store the links
public class LinkQuery{
    
    //Socket used to write a search query and then get results
    Socket socket;
    
    //string representation of the HTTP request
    String httpRequest;

    //string representation of the link to request
    String url;

    //the key for this application and cx is the custom search engine
    //created by user
    String key, cx;

    //Default constructor
    public LinkQuery(){
        httpRequest = "";
        url = "https://www.googleapis.com/customsearch/v1?key=";
        socket = new Socket("www.google.com", 80);
    }
    
    //Grab API key from file and add it to the url
    private void appendKeyFromFile(String file){
        key = "";
        
        //INSERT CODE for reading in file

        url += key;
    }

    //Grab custom search engine information from file
    private void appendCSEFromFile(String file){
        cx = "";
        
        //INSERT CODE for reading in file

        url += cx; 
    }

    //Example from website:
    //GET https://www.googleapis.com/customsearch/v1?key=INSERT_YOUR_API_KEY&cx=017576662512468239146:omuauf_lfve&q=lectures

    
    //Create the http request from the search element of interest, the file with the API key, and the file that contains the name of the custom search engine
    public void composeHTTPRequest(String searchElement, String keyFile, String cseFile){
        //Compose the URL with the search element,
        //key file and csefile
        //the append methods will create filereading objects to
        //grab the respective values
        appendKeyFromFile(keyFile);
        url += "&cx=";
        appendCSEFromFile(cseFile);
        url += "&q=" + searchElement;
        
        //Actual composition of the HTTPRequest header

        httpRequest = "GET " + url + " HTTP/1.1\r\n";
        httpRequest += "Content-Type: application/json\r\n";
        httpRequest += "\r\n\r\n";

    }

    //write the request to the socket
    public void sendRequest(){
     /*   try{
        }catch(Exception e){e.printStrackTrace();}
    */
    }
}
