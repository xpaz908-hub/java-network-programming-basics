import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        // Clear the terminal and indicate that the server is listening on port 80
        Util.clearTerminal();
        // Initialize server socket and other resources
        System.out.println("Server is listening on port 80...");

        try (Scanner scan = new Scanner(System.in);
            // Create a server socket on port 80 (listens for incoming connections)
            ServerSocket serverSocket = new ServerSocket(80);
            // Wait for a client to connect and block until a connection is made.
            // When a connection is made, returns a new socket for communication with the client
            Socket socket = serverSocket.accept();
            // Set up input and output streams for communication with the client
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()))) {
            
            Util.clearTerminal();
            System.out.println("Client connected: " + socket.getInetAddress());
            System.out.println(Util.bold("Ready to chat!\n"));

            // Enter an infinite loop to communicate with the client
            while (true) {
                System.out.print("-> ");
                String msg = bufferedReader.readLine();
                if(msg != null)
                    System.out.println("\nClient: " + msg);
                else
                    break;

                // bufferedWriter.write(scan.nextLine()); 
                // bufferedWriter.newLine();
                // bufferedWriter.flush();
            }
        } catch (Exception BaseException) {
            BaseException.printStackTrace();
        }
    }
}