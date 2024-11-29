/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less6_Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author admin
 */
public class MulticastServer {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, InterruptedException {
        DatagramSocket server = new DatagramSocket();
        System.out.println("server start to send multicast...");
        int i = 0;
        while (true) {
            String sendString = " data packet: "+i;
            byte[] buf = sendString.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf,
                    buf.length,
                    InetAddress.getByName("224.2.2.3"),
                    1107);
          
            server.send(sendPacket);
            System.out.println("mess log: "+sendString);
            Thread.sleep(1500);
            i++;
        }
    }
}
