package FinalPractice;

import UDP.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//[Mã câu hỏi (qCode): 4pTOxiuF].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:
//Đối tượng trao đổi là thể hiện của lớp UDP.Student được mô tả:
//•	Tên đầy đủ lớp: UDP.Student
//•	Các thuộc tính: id String,code String, name String, email String
//•	02 Hàm khởi tạo: 
//o	public Student(String id, String code, String name, String email)
//o	public Student(String code)
//•	Trường dữ liệu: private static final long serialVersionUID = 20171107
//Thực hiện:
//•       Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”
//b.	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Student từ server. 
//Trong đó, các thông tin được thiết lập gồm id và name.
//c.	Yêu cầu:
//-	Chuẩn hóa tên theo quy tắc: Chữ cái đầu tiên in hoa, các chữ cái còn lại in thường và gán lại thuộc tính name của đối tượng
//-	Tạo email ptit.edu.vn từ tên người dùng bằng cách lấy tên và các chữ cái bắt đầu của họ và tên đệm.
//Ví dụ: nguyen van tuan nam -> namnvt@ptit.edu.vn. Gán giá trị này cho thuộc tính email của đối tượng nhận được
//-	Gửi thông điệp chứa đối tượng xử lý ở bước c lên Server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Student đã được sửa đổi.
public class UDP_Object_StudentEmail {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        DatagramSocket socket = new DatagramSocket();

        //a
        String studentcode = ";B21DCCN157;4pTOxiuF";
        byte[] b1 = studentcode.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(b1, b1.length, InetAddress.getByName("203.162.10.109"), 2209);
        socket.send(sendPacket);

        //b
        byte[] b2 = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(b2, b2.length);
        socket.receive(receivePacket);
        String requestId = new String(receivePacket.getData(), 0, 8);
        System.out.println("requestID="+requestId);
        ByteArrayInputStream bais = new ByteArrayInputStream(b2, 8, receivePacket.getData().length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Student student = (Student) ois.readObject();
        System.out.println("Student from server: "+ student );
        String newName = chuanhoa(student.getName().trim());
        String newEmail = taoemail(student.getName().trim());
        student.setEmail(newEmail);
        student.setName(newName);
        System.out.println("update studen:"+ student);
        //c
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);
        oos.flush();
        byte[] studentData = baos.toByteArray();

        byte[] sendData = new byte[8 + studentData.length];
        System.arraycopy(requestId.getBytes(), 0, sendData, 0, 8);
        System.arraycopy(studentData, 0, sendData, 8, studentData.length);

        System.out.println("send ans: "+ sendData.toString());
        sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("203.162.10.109"), 2209);
        socket.send(sendPacket);

    }

    private static String chuanhoa(String name) {
        String res = "";
        String[] words = name.split("\\s+");
        int len = words.length;
        for (int i = 0; i < len ; i++) {
            res += words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length()).toLowerCase() ;
            if(i<len-1){
                res+= " ";
            }
        }
        return res;
    }

    private static String taoemail(String name) {
        String[] words = name.split("\\s+");
        int len = words.length;
        String res = words[len - 1].toLowerCase();
        for (int i = 0; i < len - 1; i++) {
            res += words[i].substring(0, 1).toLowerCase();
        }
        return (res + "@ptit.edu.vn");
    }

}
