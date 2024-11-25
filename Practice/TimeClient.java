import java.net.*;
import java.io.*;
import java.util.*;
class TimeClient{
    public static void main(String args[]){
        String msg="";
        try{
        Socket socket=new Socket("localhost",8082);
        System.out.println("Client Waiting....");
        DataInputStream din=new DataInputStream(socket.getInputStream());
        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
        // Scanner scan=new Scanner(System.in);
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        while(!msg.equals("end")){
        System.out.println("Enter the msg: ");
        msg=buffer.readLine();
        dout.writeUTF(msg);
        String input=din.readUTF();
        System.out.println("Server says: "+ input);
        }
        
        }
        catch(Exception e){}
    }
}