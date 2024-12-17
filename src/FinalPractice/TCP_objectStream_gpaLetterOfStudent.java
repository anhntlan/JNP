/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalPractice;

import TCP.Student;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * [Mã câu hỏi (qCode): MxpaGOUJ]. Một chương trình server cho phép kết nối qua
 * giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu
 * cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server
 * sử dụng các luồng đối tượng(ObjectOutputStream/ObjectInputStream) theo kịch
 * bản dưới đây: Biết lớp TCP.Student gồm các thuộc tính (id int,code String,
 * gpa float, gpaLetter String) và private static final long serialVersionUID =
 * 20151107;
 * a.	Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D059A3F"
 * b.	Nhận một đối tượng là thể hiện của lớp TCP.Student từ server
 * c.	Chuyển đổi giá trị điểm số gpa của đối tượng nhận được sang dạng điểm chữ và gán cho gpaLetter. Nguyên
 * tắc chuyển đổi i.	
 * 3.7 – 4 -> A ii.	3.0 – 3.7 -> B iii.	2.0 – 3.0 -> C iv.	1.0
 * – 2.0 -> D v.	0 – 1.0 -> F d. Gửi đối tượng đã được xử lý ở trên lên server.
 */
public class TCP_objectStream_gpaLetterOfStudent {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("203.162.10.109", 2209);
        System.out.println(socket);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        //a
        String student = "B21DCCN157;MxpaGOUJ";
        oos.writeObject(student);
        oos.flush();
        System.out.println("sent student code");
        
        //b
        Student st = (Student) ois.readObject();
        System.out.println("recieved Student"+ st.getCode()+ " "+ st.getGpa());
        
        //c
        float gpa = st.getGpa();
        if(gpa>=3.7){
            st.setGpaLetter("A");
        }else if(gpa >= 3.0){
            st.setGpaLetter("B");
        }else if(gpa >= 2.0){
            st.setGpaLetter("C");
        }else if(gpa >= 1){
            st.setGpaLetter("D");
        }else{
            st.setGpaLetter("F");
        }
        
        oos.writeObject(st);
        oos.flush();
        
        ois.close();
        oos.close();
        socket.close();
        
        
    }
}
