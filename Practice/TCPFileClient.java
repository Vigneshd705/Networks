import java.io.*;
import java.net.*;
class TCPFileClient{
    public static void main(String args[]){
        try{
            Socket socket=new Socket("localhost",5011);
            FileOutputStream fout=new FileOutputStream("file2.txt");
            InputStream in=socket.getInputStream();
            byte[] buffer=new byte[4096];
            int bytesRead;
            while((bytesRead=in.read(buffer))>0){
                fout.write(buffer,0,bytesRead);
            }
        }
        catch(Exception E){}
    }
}