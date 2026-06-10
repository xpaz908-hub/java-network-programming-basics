import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        // Enter an infinite loop to continuously accept client connections
        while (true) { 
            Util.clearTerminal();
            System.out.println("Server is listening on port 80...");

            try (Scanner scan = new Scanner(System.in);
                // Create a server socket on port 80 (listens for incoming connections)
                ServerSocket serverSocket = new ServerSocket(80);
                // Wait for a client to connect and block until a connection is made.
                // When a connection is made, returns a new socket for communication with the client
                Socket socket = serverSocket.accept();
                // Set up input and output streams for communication with the client
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()))
            ) {
                Util.clearTerminal();
                System.out.println("Client connected: " + socket.getInetAddress());
                System.out.println(Util.bold("Ready to chat!\n"));
                System.out.print("-> ");
                String clientMsg;
                
                // Enter an infinite loop to communicate with the client
                while (true) {
                    if ((clientMsg = reader.readLine()) != null)
                        System.out.println("\nClient: " + clientMsg);
                    
                    System.out.print("-> ");
                    writer.write(scan.nextLine());
                    writer.newLine();
                    writer.flush();
                }
            } catch (SocketException se) {
                System.out.println("Client disconnected.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}