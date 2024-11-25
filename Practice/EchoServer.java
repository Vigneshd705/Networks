import java.io.*;
import java.net.*;


class EchoServer{
    public static void main(String args[]){
        try{
            ServerSocket ss=new ServerSocket(8081);
            System.out.println("Server Started...");
            Socket socket=ss.accept();
            System.out.println("Client Connected.");
            DataInputStream din=new DataInputStream(socket.getInputStream());
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(din.readUTF());
        }
        catch(Exception e){}

    }
}