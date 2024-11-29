/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.pow;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class TCP_Luythua {

    public static void main(String[] args) throws IOException {
        String address = "172.188.19.218";
        int port = 1604;
        Socket client = new Socket(address, port);
        System.out.println(client);
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        //a
        String student = "B21DCCN157;4OPqG5r";
        os.write(student.getBytes());
        os.flush();

        //b- nhan chuoi 
        byte[] bytes = new byte[1024];
        int readbyte = is.read(bytes);
        String str = new String(bytes, 0, readbyte);

        //c-  tim luy thua
        String strnum[] = str.split("\\|");
        int a = Integer.parseInt(strnum[0]);
        int b = Integer.parseInt(strnum[1]);
        int res = (int) pow(a, b);
        os.write(Integer.toString(res).getBytes());
        os.flush();

    }

}
