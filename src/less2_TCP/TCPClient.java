/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less2_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 2207);

        System.out.println(client); // toString of object Socket

        //gui request
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeInt(100);
        dos.writeInt(200);

        //nhan response
        DataInputStream dis = new DataInputStream(client.getInputStream());
        double sum = dis.readDouble();
        System.out.println("sum: " + sum);
        dos.close();

    }

}
