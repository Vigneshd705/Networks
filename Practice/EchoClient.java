import java.io.*;
import java.net.*;

class EchoClient{
    public static void main(String args[]){
        try{
        Socket socket=new Socket("localhost",8081);
        DataInputStream din=new DataInputStream(socket.getInputStream());
        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the msg");
        String msg=buffer.readLine();
        dout.writeUTF(msg);
        System.out.println("Server says: "+din.readUTF());
        }
        catch(Exception e){}
    }
}