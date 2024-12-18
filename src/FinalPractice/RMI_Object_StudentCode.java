package FinalPractice;
//[Mã câu hỏi (qCode): 89JmMRKO].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý thông tin sinh viên trong hệ thống quản lý giáo dục. Chương trình sẽ ngẫu nhiên tạo ra đối tượng Student với các giá trị ban đầu và cung cấp cho RMI client như sau:
//
//    Giao diện từ xa:
//public interface ObjectService extends Remote {
//    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
//    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
//}
//Lớp Student gồm các thuộc tính: id String, name String, enrollmentYear int, code String.
//Trường dữ liệu: private static final long serialVersionUID = 20241130L;
//02 hàm khởi dựng:
//    public Student()
//    public Student(String id, String name, int enrollmentYear)
//Trong đó:
//    Interface ObjectService và lớp Student được viết trong package RMI.
//    Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối tượng sinh viên được nhận từ RMI Server:
//a. Triệu gọi phương thức requestObject để nhận đối tượng Student ngẫu nhiên từ server.
//b. Thực hiện 
//Tạo mã code cho sinh viên dựa trên các quy tắc sau:
//•	Bắt đầu bằng ký tự "B".
//•	Kế đến là hai chữ số cuối của enrollmentYear.
//•	Sau đó là TÊN của sinh viên, tất cả các ký tự của tên viết in hoa.
//•	Kết thúc với các chữ cái đầu tiên của Họ và Họ lót, đều viết in hoa.
//Ví dụ: Nếu sinh viên có tên là "Nguyen Van Tuan", năm nhập học là 2022, mã code sẽ là "B22TUAN_NV".
//Chuẩn hóa tên (name) theo quy tắc:
//•	Chữ cái đầu tiên của Tên, Họ, và Tên lót phải viết hoa, các chữ cái khác viết thường.
//•	Ví dụ: Nếu name ban đầu là "nguYEn van tAi tUAN", sau khi chuẩn hóa sẽ trở thành "Nguyen Van Tai TUAN".
//c. Cập nhật giá trị mã (code) và tên (name) trong đối tượng Student và 
//d. Triệu gọi phương thức submitObject để gửi đối tượng Student đã được xử lý trở lại server.
//e. Kết thúc chương trình client.

import RMI.ObjectService;
import RMI.Student;
import java.rmi.*;
import java.rmi.registry.*;

public class RMI_Object_StudentCode {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

        String studentCode = "B21DCCN157";
        String qCode = "89JmMRKO";
        Student student = (Student) objectService.requestObject(studentCode, qCode);

        System.out.println(student);
        int twolast = student.getEnrollmentYear() %100;
        String newName = chuan(student.getName());
       // System.out.println(newName);
        student.setName(newName);
        
        String nameInCode = lay(newName);
        String newCode = "B"+ String.valueOf(twolast)+ nameInCode;
      //  System.out.println(newCode);
        student.setCode(newCode);
        System.out.println("new Student: "+ student);
        objectService.submitObject(studentCode, qCode, student);
        
    }

    private static String chuan(String name) {
        String res = "";
        String[] words = name.split("\\s+");
        int len = words.length;
        for (int i = 0; i < len; i++) {
            res += words[i].substring(0, 1).toUpperCase() + words[i].substring(1, words[i].length()).toLowerCase();
              if(i < len -1)  {
                   res += " ";
              }
            
        }
       // res += words[len-1].toUpperCase();
        return res;
    }

    private static String lay(String newName) {
        String res = "";
        String[] words = newName.split("\\s+");
        int len = words.length;
        for (int i = 0; i < len-1; i++) {
            res += words[i].substring(0, 1);
            
        }
        res = words[len-1].toUpperCase() +"_"+res;
        return res;
    }
    
}
