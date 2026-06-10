package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import other.Util;

public class Server {
    private static final int port = 80;
    private static ServerSocket serverSocket;
    public static void main(String[] args) {
        Util.clearTerminal();
        try {
            // Open a TCP socket bounded to a specific port
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port 80...");

            while (true) {
                // Wait for a client to connect and block until a connection is made.
                // When a connection is made, returns a new socket for communication with the client
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());
                System.out.println(Util.bold("Ready to chat!\n"));
                // Start a new thread for each client that connects to seperately handle communcation
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // close server socket
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}