import java.net.*;

class PortScanner{
    public static void main(String args[]){
        for (int i=1;i<65335;i++){
            try{
            Socket socketr=new Socket("localhost",i);
            System.out.println("Port Available: "+i);
            }
            catch(Exception e){
                // System.out.println(e);
            }


        }
    }
}