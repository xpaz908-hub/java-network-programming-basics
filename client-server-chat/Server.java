import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Util.clearTerminal();
        // Clear the terminal and indicate that the server is listening on port 80
        System.out.println("Server is listening on port 80...");
        // Initialize server socket and other resources
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try (Scanner scan = new Scanner(System.in)) {
            serverSocket = new ServerSocket(80); // Create a server socket on port 80
            socket = serverSocket.accept();
            // Wait for a client to connect (request) and block until a connection is made.
            // When a connection is made, returns a new socket for communication with the client
            System.out.println("Client connected: " + socket.getInetAddress());
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            // Set up input and output streams for communication with the client

            System.out.println(Util.bold("Ready to chat!"));

            // Enter an infinite loop to communicate with the client
            while (true) {
                System.out.print("\n-> ");

                bufferedWriter.write(scan.nextLine()); 
                bufferedWriter.newLine();
                bufferedWriter.flush();

                while(bufferedReader.readLine() != null) {
                    System.out.println("Client: " + bufferedReader.readLine());
                }
            }
        } catch (Exception BaseException) {
            BaseException.printStackTrace();
        }
    }
}