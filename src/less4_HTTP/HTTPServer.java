/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less4_HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class HTTPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket sv = new ServerSocket(8080);
        System.out.println("runnign");
        while(true){
            Socket req = sv.accept();
            BufferedReader br = new BufferedReader(
                new InputStreamReader(req.getInputStream()));
            String line = br.readLine();
            while(!line.isEmpty() && line != null){
                System.out.println(line);
                line = br.readLine();
            }
            OutputStream os = req.getOutputStream();
            String strRes = "HTTP /1.1 200 OK \r\n\r\n";
            String strResBody = "sver is busy";
            os.write((strRes + strResBody).getBytes("UTF-8"));
            br.close();
            os.close();
            req.close();
     
            
        }
    }
}
