import java.io.*;
import java.net.*;

public class FileSenderUDP {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        FileInputStream fis = null;
        
        try {
            socket = new DatagramSocket();
            // InetAddress clientAddress = InetAddress.getByName("localhost"); // Client IP address
            // int port = 5001; // Client port number
            DatagramPacket initialPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(initialPacket);

            InetAddress clientAddress = initialPacket.getAddress();
            int port = initialPacket.getPort();

            File file = new File("file_to_send.txt"); // File to send
            fis = new FileInputStream(file);
            byte[] buffer = new byte[4096]; // Buffer to hold file chunks
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, clientAddress, port);
                socket.send(packet); // Send each chunk
            }
            // Send an empty packet as an indicator of file transfer completion
            DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, clientAddress, port);
            socket.send(endPacket);
            System.out.println("File sent successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
