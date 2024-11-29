/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class Bai1Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.188.19.218", 1605);
        
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF("B21DCCN157;sz4xgtb");

        int a = dis.readInt();
        int b = dis.readInt();
        
        System.out.println( a );
        System.out.println(b);
        int sum = a + b;
        int t = a -b;
        int product = a * b;

        dos.writeInt(sum);
        dos.writeInt(t);
        dos.writeInt(product);
        
        dos.close();
        dis.close();
        socket.close();
    }
}
