import java.io.*;
import java.net.*;

class UDPFileClient {
    public static void main(String args[]) {
        // Port number must match the server's sending port.
        int Serverport = 5000;
        String outputFile = "file3.txt";
        InetAddress serverAddress=InetAddress.getByName("localhost");
        try (
            DatagramSocket socket = new DatagramSocket();
            byte[] initData = new byte[1];
            DatagramPacket initPacket = new DatagramPacket(initData, initData.length, serverAddress, serverPort);
            socket.send(initPacket);
             FileOutputStream fout = new FileOutputStream(outputFile)) {
            BufferedOutputStream bos=new BufferedOutputStream(fout);
            System.out.println("Client started. Waiting for data...");
            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                socket.receive(packet);

                // Check for end-of-transmission signal (empty packet).
                if (packet.getLength() == 0) {
                    System.out.println("File transmission complete.");
                    break;
                }

                // Write the received data to the output file.
                bos.write(packet.getData(), 0, packet.getLength());
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
