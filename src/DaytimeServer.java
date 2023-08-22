import java.io.*;
import java.net.*;
import java.util.Date;

public class DaytimeServer {
    private static final int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Daytime Server is listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);

                    // Get the current date and time
                    Date currentDate = new Date();

                    // Write the date and time to the client
                    writer.println(currentDate.toString());

                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
