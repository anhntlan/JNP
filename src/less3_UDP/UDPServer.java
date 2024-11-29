/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less3_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author admin
 */
public class UDPServer {

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(2207);
        System.out.println("UDP server is running...");
        while (true) {
            //accept request
            byte[] buf = new byte[1024];
            DatagramPacket reP = new DatagramPacket(buf, buf.length);
            server.receive(reP);
            System.out.println(new String(reP.getData()).trim());

            //response
            byte[] sendBuf = "welcom..".getBytes();
            DatagramPacket sendP = new DatagramPacket(sendBuf,
                        sendBuf.length,
                        reP.getAddress(),
                    reP.getPort() ); // lay thong tin ng gui bang receive PAcket
            server.send(sendP);

        }

    }
}
