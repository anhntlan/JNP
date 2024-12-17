/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalPractice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.util.Arrays;

/**
 *
 * [Mã câu hỏi (qCode): TovKheaP]. Một chương trình server cho phép kết nối qua
 * giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu
 * cầu là 5 giây). Yêu cầu là xây dựng một chương trình client thực hiện kết nối
 * tới server và sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi
 * thông tin theo kịch bản sau: a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi
 * với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;X1107ABC". b. Nhận từ
 * server một chuỗi ngẫu nhiên chứa nhiều từ, các từ phân tách bởi khoảng trắng.
 * c. Thực hiện các bước xử lý: Bước 1: Tách chuỗi thành các từ dựa trên khoảng
 * trắng. Bước 2: Sắp xếp các từ theo thứ tự từ điển (có phân biệt chữ cái hoa
 * thường). d. Gửi lại chuỗi đã sắp xếp theo thứ tự từ điển lên server.
 
 8*********************************************
 * BufferedWriter = new Buff( writer = **OutputStreamWriter** ( outputStream)
 */
public class TCP_characterStream_wordSort {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2208);

        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedWriter writer = new BufferedWriter(out);
        BufferedReader reader = new BufferedReader(in);

        //a
        String student = "B21DCCN157;TovKheaP";
        writer.write(student);
        writer.newLine();
        writer.flush();

        // b
        String response = reader.readLine();
        System.out.println(response);

        //c
        String[] words = response.split("\\s+");
        Arrays.sort(words);
        String sortedStr = String.join(" ", words);
        System.out.println("sorted words:" + sortedStr);
        
        writer.write(sortedStr);
        writer.newLine();
        writer.flush();
    }
}
