import java.net.*;
import java.io.*;
import java.util.*;
class TimeServer{
    public static void main(String args[]){
        String msg="";
        try{
        ServerSocket ss=new ServerSocket(8082);
        System.out.println("Server Started....");
        Socket socket=ss.accept();
        System.out.println("Client is connected to the server");
        DataInputStream din=new DataInputStream(socket.getInputStream());
        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        String reply=din.readUTF();
        System.out.println("Client says: "+reply);
        if(reply.equals("end")){
            break;
        }
        System.out.println("Enter the reply: ");
        msg=buffer.readLine();
        dout.writeUTF(msg);
        }
        catch(Exception e){}
        
    }
}