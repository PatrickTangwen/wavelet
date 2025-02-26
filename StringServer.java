import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> words_arr = new ArrayList<String>();
    String output = "";
    public String handleRequest(URI url) {
        // if (url.getPath().startsWith("/add-message")) {
        //     return output;
        // }
        System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                words_arr.add(parameters[1]);
                for (String s : words_arr) {
                    if(output.indexOf(s) == -1) {
                        output += s + "\n"; 
                    }       	
                }
            }
        }		
        return output;
        }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
