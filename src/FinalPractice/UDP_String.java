package FinalPractice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * [Mã câu hỏi (qCode): UC0FcysZ]. [Loại bỏ ký tự đặc biệt, số, trùng và giữ
 * nguyên thứ tự xuất hiện] Một chương trình server cho phép kết nối qua giao
 * thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi
 * thông tin với server theo kịch bản dưới đây: 
 * a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ:
 * ";B15DCCN001;06D6800D" 
 * b.	Nhận thông điệp là một chuỗi từ server theo định  * dạng "requestId;strInput" •	requestId là chuỗi ngẫu nhiên duy nhất •	strInput
 * là chuỗi thông điệp cần xử lý 
 * c.	Thực hiện loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của chúng. Gửi thông điệp lên server
 * theo định dạng "requestId;strOutput", trong đó strOutput là chuỗi đã được xử
 * lý ở trên 
 * d.	Đóng socket và kết thúc chương trình.
 */
public class UDP_String {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        
        String studentCode = ";B21DCCN157;UC0FcysZ";
        byte[] buf = studentCode.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("203.162.10.109"), 2208);
        socket.send(sendPacket);
        //b
        byte[] buf2 = new byte[1024];
  
        DatagramPacket receivePacket = new DatagramPacket(buf2, buf2.length);
        socket.receive(receivePacket);
        String receiveMess = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(receiveMess);
        
        String[] parts = receiveMess.split(";");
        String requestId = parts[0];
        String strInput = parts[1];
        String strOutput = loaibo(strInput);     
        System.out.println(requestId+";"+strOutput);
        
        String ansStr = requestId+";"+strOutput;
        byte[] aa = ansStr.getBytes();
        DatagramPacket sendAnsPacket = new DatagramPacket(aa, aa.length, InetAddress.getByName("203.162.10.109"), 2208);
        socket.send(sendAnsPacket);
               

    }

    private static String loaibo(String strInput) {
        String ans = "";
        for(int i=0;i<strInput.length();i++){
            char x =strInput.charAt(i);
            if(Character.isAlphabetic(x) && !ans.contains(x+"")){
                ans+="" +x;
            }
        }
        return ans;
    }

}
