/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_TCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class Bai2Client {

    public static void main(String[] args) throws IOException {
        String address = "172.188.19.218";
        int port = 1604;
        Socket client = new Socket(address, port);

        //tao luong
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        //gui req
        String mes = "B21DCCN157;4OPqG5r";
        out.write(mes.getBytes());
        out.flush();

        byte[] bytes = new byte[1024];
        int readBytes = in.read(bytes);
        String str = new String(bytes,0,readBytes);

        String strs[] = str.split("\\|");
        int a =  Integer.parseInt(strs[0]);
        int b =  Integer.parseInt(strs[1]);
        int r = (int) Math.pow(a, b);
        System.out.println(r);
        out.write(Integer.toString(r).getBytes());
        
        in.close();
        out.close();
        client.close();
    }
}
