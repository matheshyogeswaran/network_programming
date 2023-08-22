import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simpleserver {
    public static void main(String[] args) {

        try {
            System.out.println("1.before creating the server socket");
            ServerSocket server = new ServerSocket(50001);
            System.out.println("2.after creating the server socket");

            // Create a thread pool with a fixed number of threads
            ExecutorService threadPool = Executors.newFixedThreadPool(2);

            for (;;) {
                try {
                    System.out.println("3.before accepted client");
                    Socket client = server.accept();
                    System.out.println("4.accepted client");

                    // Submit a task to the thread pool
                    threadPool.submit(new ClientHandler(client));
                } catch (IOException e) {
                    // Handle exceptions that might occur when working with a client
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Handle exceptions related to creating the server socket
            throw new RuntimeException(e);
        }
    }
}
