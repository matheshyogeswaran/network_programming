import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

class ClientHandler implements Runnable {
    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Handling client in a separate thread");

            // Prepare the response
            System.out.println("Writing data to client");
            DataOutputStream os = new DataOutputStream(client.getOutputStream());
            Date today = new Date();
            os.writeBytes("Server response: " + today.toString());

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Read input from the client after a delay
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String clientMessage = reader.readLine();
            System.out.println("Received message from client after delay: " + clientMessage);

            client.close();
            System.out.println("Closing client connection");
        } catch (IOException e) {
            // Handle exceptions that might occur when working with a client
            e.printStackTrace();
        }
    }
}
