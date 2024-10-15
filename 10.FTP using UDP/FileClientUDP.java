import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClientUDP {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            
            // Send the file name to the server
            System.out.println("Enter the file name you want from the server:");
            String requestedFileName = sc.nextLine();
            byte[] requestBuffer = requestedFileName.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(requestBuffer, requestBuffer.length, serverAddress, 5000);
            socket.send(requestPacket);
            
            // Receive server response (whether the file was found)
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);
            String serverResponse = new String(responsePacket.getData(), 0, responsePacket.getLength()).trim();
            
            if ("File found".equals(serverResponse)) {
                System.out.println("File found on server. Enter the name to save as:");
                String saveAsFilename = sc.nextLine();
                FileOutputStream fos = new FileOutputStream(saveAsFilename);
                
                // Start receiving the file data
                byte[] fileBuffer = new byte[4096];
                DatagramPacket filePacket;
                while (true) {
                    filePacket = new DatagramPacket(fileBuffer, fileBuffer.length);
                    socket.receive(filePacket);
                    
                    // Break if we receive an empty packet, indicating the end of the file
                    if (filePacket.getLength() == 0) {
                        break;
                    }
                    
                    fos.write(filePacket.getData(), 0, filePacket.getLength());
                }
                
                fos.close();
                System.out.println("File received and saved as: " + saveAsFilename);
            } else {
                System.out.println("The requested file was not found on the server.");
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
