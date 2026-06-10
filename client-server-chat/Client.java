import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Util.clearTerminal();

        try (Scanner scan = new Scanner(System.in);
            // Attempt to connect to the server at localhost on port 80
            Socket socket = new Socket("localhost", 80);
            // Set up input and output streams for communication with the client
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()))
        ) {
            System.out.println("Connected to server: " + socket.getInetAddress());
            System.out.println(Util.bold("Ready to chat!\n"));
            String serverMsg;

            // Enter an infinite loop to communicate with the server
            while (true) {
                System.out.print("-> ");
                writer.write(scan.nextLine());
                writer.newLine();
                writer.flush();

                if ((serverMsg = reader.readLine()) != null)
                    System.out.println("\nServer: " + serverMsg);
            }
        } catch (ConnectException ce) {
            System.out.println("Unable to connect to server. Make sure the server is running.");
        }
        catch (SocketException se) {
            System.out.println("Server disconnected.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}