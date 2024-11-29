/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stream;

/**
 *
 * @author admin
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Buffer {

    public static void main(String[] args) {
        String file = "d:\\4.1\\Lap_trinh_mang\\ex1.txt";
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
            }

            fis.close(); // Đóng FileInputStream sau khi sử dụng

            System.out.println(new String(buffer.toByteArray(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
