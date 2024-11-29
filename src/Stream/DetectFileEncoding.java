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
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class DetectFileEncoding {
    public static void main(String[] args) {
        String file = "d:\\4.1\\Lap_trinh_mang\\ex1.txt";
        
        // Danh sách các encodings phổ biến để kiểm tra
        List<Charset> charsetsToTry = Arrays.asList(
            Charset.forName("UTF-8"),
            Charset.forName("ISO-8859-1"),
            Charset.forName("Windows-1252"),
            Charset.forName("UTF-16"),
            Charset.forName("UTF-16LE"),
            Charset.forName("UTF-16BE")
        );
        
        for (Charset charset : charsetsToTry) {
            System.out.println("Đang thử với encoding: " + charset);
            
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(fis, charset);
                 BufferedReader reader = new BufferedReader(isr)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("-----------------------------");
        }
    }
}
