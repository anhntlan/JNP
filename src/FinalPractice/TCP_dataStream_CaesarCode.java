/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalPractice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * [Mã câu hỏi (qCode): CqOSQlAo]. Mật mã caesar, còn gọi là mật mã dịch chuyển,
 * để giải mã thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó
 * một đoạn s. Ví dụ: với s = 3 thì ký tự “A” sẽ được thay thế bằng ký tự “D”.
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ
 * trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng
 * chương trình client tương tác với server trên, sử dụng các luồng byte
 * (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự: a.	Gửi
 * một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
 * Ví dụ: "B15DCCN999;D68C93F7" b.	Nhận lần lượt chuỗi đã bị mã hóa caesar và
 * giá trị dịch chuyển s nguyên c.	Thực hiện giải mã ra thông điệp ban đầu và
 * gửi lên Server
 */
public class TCP_dataStream_CaesarCode {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2207);
        System.out.println(socket);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String student = "B21DCCN157;CqOSQlAo";
        //a
        dos.writeUTF(student);
        dos.flush();
        //b
        String strEncrypt = dis.readUTF();
        int s = dis.readInt();
        System.out.println("from server" + strEncrypt + s);
        String ans = decrypt(strEncrypt, s);
        // c
        dos.writeUTF(ans);

    }

    private static String decrypt(String strEncrypt, int s) {
        StringBuilder ans = new StringBuilder();
        for (char x : strEncrypt.toCharArray()) {
            if (Character.isAlphabetic(x)) {
                char base = Character.isUpperCase(x) ? 'A' : 'a';
                x = (char) ((x - base + s + 26) % 26 + base);

            }
            ans.append(x);
        }
        return ans.toString();
    }

}
