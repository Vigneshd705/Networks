import java.io.*;
import java.net.*;

public class FileSender {
    public static void main(String[] args) {
        String filePath = "file_to_send.txt"; // Path to the file you want to send
        int port = 5001;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for a connection on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Connected to " + socket.getInetAddress());

            // Send file
            File file = new File(filePath);
            byte[] buffer = new byte[4096];
            FileInputStream fileIn = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();

            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            fileIn.close();
            outputStream.close();
            socket.close();
            System.out.println("File sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
