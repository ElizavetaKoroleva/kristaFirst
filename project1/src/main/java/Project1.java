import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class Project1 {
    public static void main(String[] argv) throws SocketException {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;

            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();

                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            System.out.println("IP address: " + ip.getHostAddress());
                            System.out.println("Interface Name: " + netInterface.getName());

                            byte[] mac = netInterface.getHardwareAddress();

                            System.out.print("MAC address: ");

                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < mac.length; i++) {
                                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            }
                            System.out.println(sb.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Cannot get Id, something wrong with getting Ipv4 address");
        }
    }
}