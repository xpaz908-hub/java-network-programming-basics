package client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import other.Util;

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

            // Create a thread to always listen for incoming responses from the server
            new Thread(() -> {
                try {
                    String msgFromServer;
                    while ((msgFromServer = reader.readLine()) != null) {
                        System.out.println(msgFromServer);
                        System.out.print("-> ");
                    }   
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Enter loop to send requests to the server at any time
            while (true) {
                System.out.print("-> ");
                String msgToServer = scan.nextLine();
                writer.write("Client: " + msgToServer);
                writer.newLine();
                writer.flush();
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