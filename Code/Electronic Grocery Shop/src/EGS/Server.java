/*** Server side ***/

/*
*  Name : Waleed Ebrahem Mohamed
*  Sec  : 3
*/

// Package
package EGS;

// Libraries
import java.io.*;
import java.net.*;
  
// Server class
public class Server {
    
    public Server(int port)
    {
        // Initialize server socket
        ServerSocket server = null;
  
        try {
  
            // Create server socket
            server = new ServerSocket(port);
  
            // Running infinite loop for getting client request
            while (true) {
  
                // Socket object to receive incoming client requests
                Socket client = server.accept();
  
                // Displaying that new client is connected to server
                System.out.println("New client connected " + client.getInetAddress().getHostAddress());
  
                // Create a new thread object
                ClientHandler clientSock = new ClientHandler(client);
  
                // This thread will handle the client separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {}
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {}
            }
        }
    }
    
    // ClientHandler class
    private static class ClientHandler implements Runnable {
        
        // Initialize client socket
        private final Socket clientSocket;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        @Override
        public void run()
        {
            // Input and output stream objects
            BufferedReader in = null;
            PrintWriter out   = null;
           
            try {   
                // Get the outputstream of client
                out = new PrintWriter(clientSocket.getOutputStream(), true);
  
                // Get the inputstream of client
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
                // Get fruits quantities from client and store it in array
                int[] FruitsQyt = new int[3];
                String line;
                int i = 0;
                while ((line = in.readLine()) != null) {
                    
                    // Store quantities in array as int
                    FruitsQyt[i] = Integer.parseInt(line);
                    i++;
                    
                    // Writing the received message from client
                    System.out.printf("Sent from the client: %s\n", line);
                    
                    // If all values recevied
                    // Calculate total price and send it to client
                    if (i == 3)
                    {
                        // Calculate total price
                        int apple  = FruitsQyt[0];
                        int banana = FruitsQyt[1];
                        int orange = FruitsQyt[2];
                        int total = apple*30 + banana*40 + orange*50;

                        // Send total price to the client
                        out.println(total);
                    }
                }                
            }
            catch (IOException e) {}
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {}
            }
        }
    }
    
    // Main
    public static void main(String[] args)
    {
        Server server = new Server(5000);
    }
}