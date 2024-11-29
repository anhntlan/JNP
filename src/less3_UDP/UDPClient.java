/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less3_UDP;

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
public class UDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] data = new byte[1024];
        String strSend = "Hello from UDPClient";
        data = strSend.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                data,
                data.length,
                InetAddress.getByName("localhost"),
                2207);
        
        client.send(sendPacket);
        System.out.println("send success");
        //receive 
        byte[] rBuf = new byte[1024];
        DatagramPacket recP = new DatagramPacket(rBuf, rBuf.length);
        client.receive(recP);
            System.out.println(new String(recP.getData()).trim());
    }
}
