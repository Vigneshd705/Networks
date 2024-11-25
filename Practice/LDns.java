import java.net.*;
import java.util.*;
class LDns{
    public static void main(String args[]){
        String ipAddress=null;
        try{
        Process process=Runtime.getRuntime().exec("nslookup");
        Scanner scanner=new Scanner(process.getInputStream());
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            if(line.startsWith("Address")){
                ipAddress=line.split(":")[1].trim();
                System.out.println(ipAddress);
                break;
            }

        }
                if(ipAddress!=null){
                InetAddress inetAddress=InetAddress.getByName(ipAddress);
                String localhost=inetAddress.getHostName();
                System.out.println(localhost);
            }
            String HostName="www.amazon.com";
            InetAddress[] addressess=InetAddress.getAllByName(HostName);
            for (InetAddress address:addressess){
                System.out.println(address.getHostAddress());
            }
        }
        catch(Exception e){}

    }
}