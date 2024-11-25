import java.net.*;
import java.util.*;

class RouterIP{
    public static void main(String args[]){
        String ipAddress="";
        try{
            Process process = Runtime.getRuntime().exec("ipconfig");
            Scanner reader = new Scanner(process.getInputStream());
            String line;
            boolean ip=false;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                if (line.trim().startsWith("Default Gateway")) {
                    ipAddress = line.substring(line.indexOf(":") + 1);
                    System.out.println("Router IP Address: " + ipAddress);
                    ip=true;
                }
                if(ip){
                    line=reader.nextLine();
                    ipAddress=line.trim();
                    ip=false;
                }
            }
            process=Runtime.getRuntime().exec("arp -a");
            reader=new Scanner(process.getInputStream());
            while(reader.hasNextLine()){
                line=reader.nextLine();
                if (line.trim().startsWith(ipAddress)){
                    System.out.println("Mac Address: "+line.split("\s\s+")[2]);
                }
            }
        }
        catch(Exception e){
            
        }
    }
}