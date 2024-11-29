/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class Bai1 {

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("connecting..");

            Socket socket = new Socket("172.188.19.218", 1605);

            boolean status = socket.isConnected();
            if (status) {
                System.out.println("connected successfully");
            }
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("B21DCCN157;sz4xgtb");

            while (status) {
                int a = dis.readInt(); // Có thể gây ra EOFException
                int b = dis.readInt();
                System.out.println(a);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
