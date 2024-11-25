import java.io.*;
import java.net.*;

class TCPFileServer{
    public static void main(String args[]){
        FileInputStream fin;
        OutputStream out;
        File file=new File("file.txt");
        try{
            ServerSocket ss=new ServerSocket(5011);
            System.out.println("Server is ready...");
            Socket socket=ss.accept();
            fin=new FileInputStream(file);
            out=socket.getOutputStream();
            byte[] buffer =new byte[4096];
            int bytesRead;
            while((bytesRead=fin.read(buffer))>0){
                out.write(buffer,0,bytesRead);
            }
        }
        catch(Exception e){}

    }
}