import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server IP address
        int port = 5000; // Server port
        String savePath = "./file2.txt"; // Path to save the file

        try (Socket socket = new Socket(serverAddress, port);
             FileOutputStream fileOutputStream = new FileOutputStream(savePath);
             BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream())) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
