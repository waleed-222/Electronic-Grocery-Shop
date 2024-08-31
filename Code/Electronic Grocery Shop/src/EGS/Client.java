/*** Client side ***/

/*
*  Name : Waleed Ebrahem Mohamed
*  Sec  : 3
*/

// Package
package EGS;

// Libraries
import java.io.*;
import java.net.*;
  
// Client class
public class Client {
    
    // Objects initialization
    public Socket socket     = null;
    public PrintWriter out   = null;
    public BufferedReader in = null;
    
    public Client(int port) throws IOException
    {   
        // Create socket
        socket = new Socket("127.0.0.1", port);
    }

    // Method to send data from client to server
    public void sendData(int data1, int data2, int data3) throws IOException
    {
        // PrintWriter object that sending data to server
        out = new PrintWriter(socket.getOutputStream(), true);

        // Sending fruits quantities values to the server
        out.println(data1);
        out.println(data2);
        out.println(data3);
    }
    
    // Method to recevie data from server
    public int receiveData() throws IOException
    {
        // BufferedReader Object that reading data from server
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Read server reply which is the total price
        int total = Integer.parseInt(in.readLine());
        
        // Return result
        return total;
    }
}
