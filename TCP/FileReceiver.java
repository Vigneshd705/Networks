import java.io.*;
import java.net.*;

public class FileReceiver {
    public static void main(String[] args) {
        String saveAs = "received_file.txt"; // Path to save the received file
        String host = "localhost"; // Server address
        int port = 5001;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server at " + host + ":" + port);

            // Receive file
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOut = new FileOutputStream(saveAs);
            byte[] buffer = new byte[4096];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                fileOut.write(buffer, 0, bytesRead);
            }

            fileOut.close();
            inputStream.close();
            System.out.println("File received and saved as '" + saveAs + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
