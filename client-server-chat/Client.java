import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Clear the terminal and initialize resources for the client
        Util.clearTerminal();

        try (Scanner scan = new Scanner(System.in);
             // Attempt to connect to the server at localhost on port 80
            Socket socket = new Socket("localhost", 80);
            // Set up input and output streams for communication with the client
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()))) {
            
            System.out.println("Connected to server: " + socket.getInetAddress());
            System.out.println(Util.bold("Ready to chat!\n"));

            while (true) {
                System.out.print("-> ");
                bufferedWriter.write(scan.nextLine());
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                // String msg = bufferedReader.readLine();
                // if(msg != null)
                //     System.out.println("\nServer: " + msg);
                // else
                //     break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}