import java.net.*;
class HostNameandIPaddress{
    public static void main(String args[]){
        try{
        InetAddress localhost=InetAddress.getLocalHost();
        System.out.println("Hostname: "+localhost.getHostName());
        System.out.println("HostAddress: "+localhost.getHostAddress());
        }
        catch(Exception e){

        }
    }
}