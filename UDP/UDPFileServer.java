import java.io.*;
import java.net.*;

public class UDPFileServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(5000); // Server listens on port 5000
            System.out.println("Server is running on port 5000...");

            // Wait for the initial packet from the client to get its address and port
            byte[] buffer = new byte[1024];
            DatagramPacket initialPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(initialPacket);

            InetAddress clientAddress = initialPacket.getAddress();
            int clientPort = initialPacket.getPort();
            System.out.println("Client connected: " + clientAddress + ":" + clientPort);

            // Open the file to be sent
            File file = new File("file.txt"); // Replace with your input file name
            if (!file.exists()) {
                System.out.println("File not found. Exiting.");
                return;
            }

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Send the file in chunks
            byte[] fileBuffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(fileBuffer)) != -1) {
                DatagramPacket filePacket = new DatagramPacket(fileBuffer, bytesRead, clientAddress, clientPort);
                socket.send(filePacket);
            }

            // Send an empty packet to indicate the end of the file
            socket.send(new DatagramPacket(new byte[0], 0, clientAddress, clientPort));
            bis.close();
            System.out.println("File transfer completed.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
