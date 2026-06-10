package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable  {
    Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override 
    public void run() {
    try (Scanner scan = new Scanner(System.in);
            // Set up input and output streams for communication with the client
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()))
        ) {
            // Create thread to constantly listen for incoming requests from the client
            new Thread(() -> {
                try {
                    String msgFromClient;
                    while ((msgFromClient = reader.readLine()) != null) {
                        System.out.println(msgFromClient);
                        System.out.print("-> ");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Enter loop to respond/write data back to the client at any time
            while (true) { 
                System.out.print("-> ");
                String msgToClient = scan.nextLine();
                writer.write("Server: " + msgToClient);
                writer.newLine();
                writer.flush();
            }
        } catch (Exception e) {
        }
    }
}