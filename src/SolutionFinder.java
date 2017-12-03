import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.*;

//This class implements a webscraper to grab the pages that are related to the error
//This would use a search engine, most likely Google, to find the links.
//--we could try this link: https://developers.google.com/custom-search/json-api/v1/overview
public class SolutionFinder{
    
    //Class used to search on StackOverflow and then store the links
    public class LinkQuery{
        
        //Socket used to write a search query and then get results
        SSLSocket socket;
        
        //string representation of the HTTP request
        String httpRequest;
    
        //string representation of the link to request
        String url;
    
        //Default constructor
        public LinkQuery(){
            httpRequest = "";
            url = "/customsearch/v1?key=";
            
            try{
            
                //Setup the SSL context
                SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            
                socket = (SSLSocket) factory.createSocket("www.google.com", 443);
                socket.startHandshake();
            
            }catch(Exception e){e.printStackTrace();}
        }
        
        //Grab API key from file and add it to the url
        private void appendKeyFromFile(String file){
            String key = "";
            try{
                BufferedReader in = new BufferedReader(new FileReader(file));
                key = in.readLine();
                
                in.close();
            }catch(Exception e){}
    
            url += key;
        }
    
        //Grab custom search engine information from file
        private void appendCSEFromFile(String file){
            String cx = "";
            
            try{
                BufferedReader in = new BufferedReader(new FileReader(file));
                cx = in.readLine();
                
                in.close();
            }catch(Exception e){}
    
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
            httpRequest += "User-Agent: Mozilla/5.0 (X11; Linux x86_64)\r\n";
            httpRequest += "Connection: close\r\n";
            httpRequest += "\r\n\r\n";
    
        }
    
        //write the request to the socket
        public void sendRequest(){
            try{
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                //DEBUG
                System.out.println("HTTP Request: \n" + httpRequest);

                out.write(httpRequest);
                out.flush();

            }catch(Exception e){e.printStackTrace();}
        }
        
        //Return the response from the HTTP Request
        String getResponse(){
            String response = "";
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                //Read in entire response
                while((response += in.readLine()) != null){
                    response += "\n";
                    //DEBUG
                    System.out.println("Response: " + response);
                }
                
                //DEBUG
                System.out.println("HTTP Response: \n" + response);

            }catch(Exception e){e.printStackTrace();}
            return response;
        }

        //Method to close the socket connection
        void close(){
            try{
                socket.close();
            }catch(Exception e){e.printStackTrace();}
        }

    }
    //End of LinkQuery class definition


    //Private variables

    //A list of all of the links to various websites
    List<URL> links;
    
    //Object that wraps a lot of socket information
    LinkQuery lq;

    //Info for request
    //The key file
    //cx name file
    //and the search element of interest
    String key, cx, se;

    //Default contructor
    public SolutionFinder(String se, String key, String cx){
        links = new ArrayList<URL>();
        lq = new LinkQuery();
        this.key = key;
        this.cx = cx;
        this.se = se;
    }

    //Compose request header
    //Send request and receive response
    //parser response to get the individual links
    public void getLinks(){
        //Construct and send HTTP Request
        lq.composeHTTPRequest(se, key, cx);
        lq.sendRequest();
        
        //Receive and parse the response
        String response = lq.getResponse();
        

        //Close the connection
        lq.close();
    }

    //Download the URL webpages to display
    //Returns a list of all the webpages in a string format
    public String[] getPlainTextPages(){
        
        //Open each link and download the webpages into String
        for(int i = 0; i < links.size(); ++i){
            
        }

        return null;
    }

    //Save a list of webpages into base file
    void savePagesToFile(String file){
        
    }

    public static void main(String[] args){
        SolutionFinder sf = new SolutionFinder("rust", "key.txt", "cx.txt");
        sf.getLinks();
    }

}


