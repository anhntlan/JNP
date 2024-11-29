/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Ex1 {

    public static void main(String[] args) throws IOException {
        String file = "d:\\4.1\\Lap_trinh_mang\\ex1.txt";
//        String file2 = "d:\\4.1\\Lap_trinh_mang\\ex2.txt";
//
        try (FileInputStream fis = new FileInputStream(file); //  FileOutputStream fos = new FileOutputStream(file2);
                ) {
            byte[] data = new byte[1024];
            while (fis.available() > 0) {
                fis.read(data);
            }
            //     fos.write(data);
            String tmp_str = new String(data, "UTF-8");
            System.out.println(new String(data, "UTF-8"));
            
//            System.out.println(new String(data,StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e.getCause());
//              }
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(file);
//
//            // int data = 0;
//            byte[] data = new byte[1024];
//            //   while((data= fis.read())!=-1)
//            while (fis.available() > 0) {
//                fis.read(data);
//            }
//            System.out.print(new String(data));
//        } catch (FileNotFoundException ex) {
//            System.out.println("file not exist");
//            Logger.getLogger(Ex1.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (fis != null)
//                try {
//                fis.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Ex1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//
        }
    }
}
