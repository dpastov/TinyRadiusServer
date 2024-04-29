import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TinyRadiusServer {

    public static void main(String[] args) {
        final int portNumber = 20001;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("TinyRadiusServer (v1.0.0) started on port " + portNumber);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received message from client: " + inputLine);
                        out.println("Server echoed: " + inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
