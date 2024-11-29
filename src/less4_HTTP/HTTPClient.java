/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less4_HTTP;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class HTTPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("google.com",80);
        System.out.println(client);
        //send
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String strReq = "GET / HTTP/1.1 \r\n"
                +"Host: google.com \r\n"
                +"USer-Agent: Client 1.0 \r\n"
                +"Accept: text/* \r\n"
                +"\r\n";
        br.write(strReq);
        br.flush();
        System.out.println("sen req suss");
     
        //recieve res
        InputStream is = client.getInputStream();
        byte [] data = new byte[1024];
        int bytesReaded = 0;
        while((bytesReaded = is.read(data)) != -1){
            System.out.println(new String(data,0,bytesReaded,"UTF-8"));
        }
        br.close();
        is.close();
                
    }
}
