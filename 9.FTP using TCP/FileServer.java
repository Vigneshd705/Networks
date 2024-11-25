
import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 5000; // Port to listen on
        String filePath = "./file.txt"; // File to send

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected to client");

                try (FileInputStream fileInputStream = new FileInputStream(filePath);
                    BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("File sent successfully");
                } catch (IOException e) {
                    System.out.println("Error sending file: " + e.getMessage());
                }

                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
