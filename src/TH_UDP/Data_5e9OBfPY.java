/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 * @author admin [Mã câu hỏi (qCode): 5e9OBfPY]. Một chương trình server cho
 * phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một
 * chương trình client trao đổi thông tin với server theo kịch bản: a. Gửi thông
 * điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
 * “;studentCode;qCode”. Ví dụ: “;B15DCCN004;99D9F604” b. Nhận thông điệp là một
 * chuỗi từ server theo định dạng “requestId;z1,z2,...,z50” requestId là chuỗi
 * ngẫu nhiên duy nhất z1 -> z50 là 50 số nguyên ngẫu nhiên c. Thực hiện tính số
 * lớn thứ hai và số nhỏ thứ hai của thông điệp trong z1 -> z50 và gửi thông
 * điệp lên server theo định dạng “requestId;secondMax,secondMin” d. Đóng socket
 * và kết thúc chương trình
 */
public class Data_5e9OBfPY {


    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        //a
        String strsend = ";B21DCCN157;5e9OBfPY";
        byte[] buf = strsend.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("203.162.10.109"), 2207);

        client.send(sendPacket);
        //b
        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        String recMess = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("nhan tu server: " + recMess);

        // Phân tách requestId và danh sách số nguyên
        String[] parts = recMess.split(";");
        String requestId = parts[0];
        String[] numbersStr = parts[1].split(",");

        // Chuyển chuỗi các số thành mảng số nguyên
        int[] numbers = Arrays.stream(numbersStr).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);
        // c. Tìm số lớn thứ hai và số nhỏ thứ hai trong z1 -> z50
        int secondMax = numbers[numbers.length-2];
        int secondMin = numbers[1];
        System.out.println("Số lớn thứ hai: " + secondMax);
        System.out.println("Số nhỏ thứ hai: " + secondMin);

        // Gửi lại thông điệp với requestId, secondMax, secondMin
        String responseMessage = requestId + ";" + secondMax + "," + secondMin;
        byte[] responseData = responseMessage.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(responsePacket);
    }
}
