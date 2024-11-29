/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 * @author admin
 *
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
 * “;studentCode;qCode”. Ví dụ: “;B15DCCN001;DC73CA2E” b.	Nhận thông điệp là một
 * chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” -	requestId là chuỗi
 * ngẫu nhiên duy nhất -	a1 -> a50 là 50 số nguyên ngẫu nhiên c.	Thực hiện tìm
 * giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi thông
 * điệp lên lên server theo định dạng “requestId;max,min” d.	Đóng socket và kết
 * thúc chương trình
 */
public class Data_YvgQnJQH {

    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        DatagramSocket client = new DatagramSocket();
        //a
        String strsend = ";B21DCCN157;YvgQnJQH";
        byte[] buf = new byte[1024];
        buf = strsend.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("203.162.10.109"), 2207);

        client.send(sendPacket);
        //b
        byte[] reData = new byte[1024];
        DatagramPacket rePacket = new DatagramPacket(reData, reData.length);
        client.receive(rePacket);
        String reStr = new String(rePacket.getData(), 0, rePacket.getLength());
        System.out.println("nhan tu server:" + reStr);

        //c
        String[] reStrArr = reStr.split(";");
        String qsID = reStrArr[0];
        String[] nums = reStrArr[1].split(",");

        int[] numbers = Arrays.stream(nums).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);
        int maxnumber = numbers[numbers.length - 1];
        int minnumber = numbers[0];
        System.out.println("min: " + minnumber);
        System.out.println("max: " + maxnumber);
        //d
        String ans = qsID + ";" + maxnumber + "," + minnumber;
        byte[] buff = ans.getBytes();
        DatagramPacket sendAnsPacket = new DatagramPacket(buff, buff.length, InetAddress.getByName("203.162.10.109"), 2207);
        client.send(sendAnsPacket);
    }

}
