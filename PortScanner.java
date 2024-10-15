import java.net.*;
public class PortScanner{ 
public static void main(String[] args) {
    for(int i=1;i<=65535;i++){
        try{
            Socket sc=new Socket("localhost",i);
            System.out.println("Port " + i + " is Open");
        }
        catch(Exception e){
            
        }
    }   
}
}