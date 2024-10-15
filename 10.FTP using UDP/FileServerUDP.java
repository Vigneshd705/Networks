import java.io.*;
import java.net.*;

public class FileServerUDP {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(5000); // Bind the socket to port 5000
            System.out.println("Server is listening on port 5000...");

            while (true) { // Keep the server running indefinitely
                try {
                    // Receive the requested file name from the client
                    byte[] buffer = new byte[1024];
                    DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                    socket.receive(requestPacket);  // Wait for client request
                    String requestedFileName = new String(requestPacket.getData(), 0, requestPacket.getLength()).trim();
                    InetAddress clientAddress = requestPacket.getAddress();
                    int clientPort = requestPacket.getPort();

                    // Check if the file exists
                    File file = new File(requestedFileName);
                    byte[] responseBuffer = new byte[1024];

                    if (file.exists() && !file.isDirectory()) {
                        // File found, inform the client
                        responseBuffer = "File found".getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, clientAddress, clientPort);
                        socket.send(responsePacket);

                        // Send the file data
                        FileInputStream fis = new FileInputStream(file);
                        byte[] fileBuffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = fis.read(fileBuffer)) != -1) {
                            DatagramPacket filePacket = new DatagramPacket(fileBuffer, bytesRead, clientAddress, clientPort);
                            socket.send(filePacket);
                        }

                        // Send an empty packet to indicate the end of the file
                        DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, clientAddress, clientPort);
                        socket.send(endPacket);

                        fis.close();
                        System.out.println("File sent successfully to client.");
                    } else {
                        // File not found, inform the client
                        responseBuffer = "File not found".getBytes();
                        DatagramPacket errorPacket = new DatagramPacket(responseBuffer, responseBuffer.length, clientAddress, clientPort);
                        socket.send(errorPacket);
                        System.out.println("File not found on server.");
                    }
                } catch (IOException ex) {
                    System.err.println("Error processing request: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
