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

/**
 *
 * @author admin [Mã câu hỏi (qCode): SEPXUmrs]. Một chương trình server cho
 * phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương
 * trình client trao đổi thông tin với server theo kịch bản dưới đây: a.	Gửi
 * thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
 * “;studentCode;qCode”. Ví dụ: “;B15DCCN001;5B35BCC1” b.	Nhận thông điệp từ
 * server theo định dạng “requestId;data” -	requestId là một chuỗi ngẫu nhiên
 * duy nhất -	data là chuỗi dữ liệu cần xử lý c.	Xử lý chuẩn hóa chuỗi đã nhận
 * thành theo nguyên tắc i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa ii.
 * Các ký tự còn lại của chuỗi là in thường Gửi thông điệp chứa chuỗi đã được
 * chuẩn hóa lên server theo định dạng “requestId;data” d.	Đóng socket và kết
 * thúc chương trình
 */
public class String_SEPXUmrs {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {

        DatagramSocket client = new DatagramSocket();
        //a
        String strsend = ";B21DCCN157;SEPXUmrs";
        byte[] buf = new byte[1024];
        buf = strsend.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("203.162.10.109"), 2208);

        client.send(sendPacket);
        //b
        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        String recMess = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("nhan tu server: " + recMess);

        String[] parts = recMess.split(";");
        String requestID = parts[0];
        String dataToProcess = parts[1];

        //xu li tung tu trong chuoi
        String[] words = dataToProcess.split("\\s+");
        StringBuilder pro = new StringBuilder();
        for (String w : words) {
            String proWord = w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase();
            pro.append(proWord).append(" ");

        }

        String sendBackMess = requestID + ";" + pro.toString().trim();
        buf = sendBackMess.getBytes();
        sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("203.162.10.109"), 2208);
        client.send(sendPacket);

    }
}
