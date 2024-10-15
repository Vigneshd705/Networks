import java.io.*;
import java.net.*;

public class FileReceiverUDP {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        FileOutputStream fos = null;
        
        try {
            socket = new DatagramSocket(5001); // Server will send to this port
            fos = new FileOutputStream("received_file.txt"); // File to save the received data
            
            byte[] buffer = new byte[4096]; // Buffer for receiving file chunks

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Receive the packet

                if (packet.getLength() == 0) { // Check if this is the end-of-file signal
                    break;
                }

                fos.write(packet.getData(), 0, packet.getLength()); // Write chunk to file
            }

            System.out.println("File received successfully.");
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
