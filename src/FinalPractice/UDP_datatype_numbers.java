package FinalPractice;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/* Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;371EA16D”
b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId; n; A1,A2,...An”, với
-	requestId là chuỗi ngẫu nhiên duy nhất
-	n là một số ngẫu nhiên nhỏ hơn 100.
-            A1, A2 ... Am với m <= n là các giá trị nguyên liên tiếp, nhỏ hơn hoặc bằng n và không trùng nhau.
Ví dụ: requestId;10;2,3,5,6,9 
c.	Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”
Ví dụ: requestId;1,4,7,8,10
d.	Đóng socket và kết thúc chương trình.
 */
public class UDP_datatype_numbers {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        //a
        String studentCode = ";B21DCCN157;gL37a4b6";
        byte[] buf = studentCode.trim().getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("203.162.10.109"), 2207);
        socket.send(packet);
        //b
        byte[] receive = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
        socket.receive(receivePacket);
        String receiveMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(receiveMessage);

        // c
        String[] parts = receiveMessage.split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);
        String[] numbers = parts[2].split(",");

        int[] numbersArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numbersArray[i] = Integer.parseInt(numbers[i]);
        }
        Arrays.sort(numbersArray);
        StringBuilder ans = new StringBuilder();
        int idx = 0;

        for (int i = 1; i <= n; i++) { // requestId;10;2,3,5,6,9 => 2 3 5 6 9 10 => 
            if (idx < numbersArray.length && i == numbersArray[idx]) {
                idx++;
            } else {
                ans.append(i).append(",");
            }
        }
        if(ans.charAt(ans.length()-1)==','){
            ans.deleteCharAt(ans.length()-1);
        }

        //send
        String ansStr = requestId +";"+ ans.toString();
        System.out.println(ansStr);
        byte[] buf2 = ansStr.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(buf2, buf2.length, InetAddress.getByName("203.162.10.109"), 2207);
        socket.send(sendPacket2);
    }
}
