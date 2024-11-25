import java.io.*;
import java.net.*;

public class UDPFileClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 5000;

            // Send an initial packet to the server to initiate file transfer
            byte[] initData = new byte[1];
            DatagramPacket initPacket = new DatagramPacket(initData, initData.length, serverAddress, serverPort);
            socket.send(initPacket);

            // Prepare to receive the file
            FileOutputStream fos = new FileOutputStream("received_file.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[4096];
            DatagramPacket filePacket = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(filePacket);
                if (filePacket.getLength() == 0) { // Empty packet indicates end of file
                    break;
                }
                bos.write(filePacket.getData(), 0, filePacket.getLength());
            }

            bos.close();
            System.out.println("File transfer completed. File saved as 'received_file.txt'.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
