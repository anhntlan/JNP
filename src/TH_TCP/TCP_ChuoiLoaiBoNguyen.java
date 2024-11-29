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
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class TCP_ChuoiLoaiBoNguyen {

    public static void main(String[] args) throws IOException {
        String address = "172.188.19.218";
        int port = 1606;
        Socket client = new Socket(address, port);
        System.out.println(client);
        Writer out = new OutputStreamWriter(client.getOutputStream());
        BufferedWriter writer = new BufferedWriter(out);
        Reader in = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(in);

        //a
        String student = "B21DCCN157;pf67SkS";
        writer.write(student);
        writer.newLine();
        writer.flush();

        //b
        String strFromSer = reader.readLine();
        System.out.println(strFromSer);
        String strWrite = strFromSer.replaceAll("[euioa]", "");

        writer.write(strWrite);
        writer.newLine();
        writer.flush();

        reader.close();
        writer.close();
        client.close();

    }
}
