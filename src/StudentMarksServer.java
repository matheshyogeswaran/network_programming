import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class StudentMarksServer {
    private static final int PORT = 8;
    private static Map<String, Integer> studentMarks = new HashMap<>();

    static {

        studentMarks.put("1", 97);
        studentMarks.put("2", 89);
        studentMarks.put("3", 56);

    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Student Marks Server is listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        System.out.println("Received Index Number: " + inputLine);

                        Integer totalMarks = studentMarks.get(inputLine);
                        if (totalMarks != null) {
                            writer.println("Total Marks for Index " + inputLine + ": " + totalMarks);
                        } else {
                            writer.println("Invalid Index Number");
                        }
                    }

                    System.out.println("Client disconnected");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
