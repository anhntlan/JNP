/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class Bai3Client {
    public static String lbna(String str) {
    return str.replaceAll("[euoia]", "");
}

    public static void main(String[] args) throws IOException {

        String add = "172.188.19.218";
        int port = 1606;
        Socket client = new Socket(add, port);
        System.out.println(client);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        writer.write("B21DCCN157;pf67SkS");
        writer.newLine();
        writer.flush();

        System.out.println("send a string to server");
        String str = reader.readLine();
        System.out.println(str);
        if (str == null) {
            System.out.println("Không nhận được dữ liệu từ server.");
            client.close();
            return; // Kết thúc 
        }
        String result = lbna(str);
        System.out.println(result);

        writer.write(result);
        writer.newLine();
        writer.flush();
        
        
        writer.close();
        reader.close();
        client.close();
    }
}
