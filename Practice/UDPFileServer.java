import java.net.*;
import java.io.*;
class UDPFileServer{
    public static void main(String args[]){
        File file=new File("file.txt");
        DatagramSocket socket;
        try{
            socket=new DatagramSocket(5000);
            System.out.println("Server Stated..");
            FileInputStream fin=new FileInputStream(file);
            BufferedInputStream bis=new BufferedInputStream(fin);
            byte[] buffer=new byte[4096];
            InetAddress clientAddress=InetAddress.getByName("localhost");
            int bytesRead;
            while((bytesRead=bis.read(buffer))>0){
                DatagramPacket packet=new DatagramPacket(buffer,bytesRead,clientAddress,5011);
                socket.send(packet);
            }
            DatagramPacket endPacket=new DatagramPacket(new byte[0],0,clientAddress,5011);
            socket.send(endPacket);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}