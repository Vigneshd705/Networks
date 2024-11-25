import java.io.IOException;
import java.util.Scanner;

class RouterIP {
    public static void main(String[] args) {
        String ipAddress = "";
        try {
            Process process = Runtime.getRuntime().exec("ipconfig");
            Scanner reader = new Scanner(process.getInputStream());
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                if (line.trim().startsWith("Default Gateway")) {
                    int colonIndex = line.indexOf(":");
                    if (colonIndex != -1) { // Ensure ':' is present
                        ipAddress = line.substring(colonIndex + 1).trim();
                        if (!ipAddress.isEmpty())
                        System.out.println("Router IP Address: " + ipAddress);
                    } else {
                        System.out.println("Default Gateway format is incorrect.");
                    }
                }
            }
            reader.close(); // Close the Scanner

            if (ipAddress.isEmpty()) {
                System.out.println("Router IP Address not found.");
                return; // Exit if no IP address is found
            }

            process = Runtime.getRuntime().exec("arp -a");
            reader = new Scanner(process.getInputStream());
            boolean macFound = false;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                if (line.trim().startsWith(ipAddress)) {
                    String[] parts = line.split("\\s+"); // Split by whitespace
                    if (parts.length >= 2) { // Ensure there are enough parts
                        String macAddress = parts[1];
                        System.out.println("Router MAC Address: " + macAddress);
                        macFound = true;
                    }
                    break;
                }
            }
            reader.close(); // Close the Scanner

            if (!macFound) {
                System.out.println("MAC Address not found for IP Address: " + ipAddress);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
