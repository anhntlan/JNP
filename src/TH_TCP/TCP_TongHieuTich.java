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
public class TCP_TongHieuTich {

    public static void main(String[] args) throws IOException {
        String address = "172.188.19.218";
        int port = 1605;
        Socket client = new Socket(address, port);

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        //a
        String student = "B21DCCN157;sz4xgtb";
        dos.writeUTF(student);

        int a = dis.readInt();
        int b = dis.readInt();
        System.out.println(a);
        System.out.println(b);
        int tong = a + b;
        int hieu = a - b;
        int tich = a * b;

        dos.writeInt(tong);
        dos.writeInt(hieu);
        dos.writeInt(tich);

        dis.close();
        dos.close();
        client.close();

    }
}
