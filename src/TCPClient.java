import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TCPClient <server_ip> <server_port>");
            return;
        }

        String serverIP = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(serverIP, serverPort)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = reader.readLine();
            System.out.println("Server Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
