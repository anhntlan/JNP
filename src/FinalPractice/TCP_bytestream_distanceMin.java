package FinalPractice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * [Mã câu hỏi (qCode): KoTNwXug].Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;FF49DC02"
 * b.	Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký tự ","
 * Ex: 1,3,9,19,33,20
 * c.	Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và hai giá trị lớn nhất tạo nên khoảng cách đó. Gửi lên server chuỗi gồm "khoảng cách nhỏ nhất, số thứ nhất, số thứ hai". Ex: 1,19,20
***********************************************
* InputStream,OutputStream = socket.getInputStream()
* os.write(String.getBytes());
* int readbyte = is.read(bytes); String response = new String(bytes,0,readbyte);

*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class TCP_bytestream_distanceMin {

    public static void main(String[] args) throws IOException {
        String address = "203.162.10.109";
        int SERVER_PORT = 2206;

        Socket socket = new Socket(address, SERVER_PORT);
        System.out.println(socket);
        
        InputStream is = socket.getInputStream();
        OutputStream os =  socket.getOutputStream();
        
        //a sen student code
        String student = "B21DCCN157;KoTNwXug";
        os.write(student.getBytes());
        os.flush();

        // Step b: Receive STRING from server
        byte[] bytes = new  byte[1024];
        int readbyte = is.read(bytes);
        String response = new String(bytes,0, readbyte);
        System.out.println("Received from server: " + response);

        // Parse the received data
        String[] numbersStr = response.split(",");
        int len = numbersStr.length;
        int[] numberInt = new int[len];
        for (int i = 0; i < len; i++) {
            numberInt[i] = (Integer.parseInt(numbersStr[i]));
        }

        // Step c: Find the minimum distance and the two largest numbers
        Arrays.sort(numberInt);

        int minDis = Integer.MAX_VALUE;
        int[] pair = new int[2];

        //
        for (int i = len - 1; i > 0; i--) {
            int d = numberInt[i] - numberInt[i - 1];
            if (d < minDis) {
                minDis = d;
                pair[0] = numberInt[i - 1];
                pair[1] = numberInt[i];
            }
        }

        // Step d: Send back the result to the server
        String resultMessage = minDis + "," + pair[0] + "," + pair[1];
        os.write(resultMessage.getBytes());
        System.out.println("Sent to server: " + resultMessage);

    }

}
