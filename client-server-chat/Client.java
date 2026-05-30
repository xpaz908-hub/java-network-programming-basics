import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Clear the terminal and initialize resources for the client
        Util.clearTerminal();
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try (Scanner scan = new Scanner(System.in)) {
            socket = new Socket("0.0.0.0", 80); // Connect to the server at any network interface on port 80 (sends a request)
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("Connected to server: " + socket.getInetAddress());
            System.out.println(Util.bold("Ready to chat!"));
            System.out.print("\n-> ");

            while (true) {
                System.out.print("\n-> ");
                
                bufferedWriter.write(scan.nextLine());
                bufferedWriter.newLine();
                bufferedWriter.flush();

                while(bufferedReader.readLine() != null) {
                    System.out.println("Server: " + bufferedReader.readLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}