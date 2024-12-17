/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalPractice;

//[Mã câu hỏi (qCode): Knb63v68].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
//Giao diện từ xa:
//public interface DataService extends Remote {
//public Object requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
//}
//Trong đó:
//•	Interface DataService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một chuỗi chứa mảng số nguyên và một số nguyên K từ server với định dạng: “mảng; số nguyên K”.
//b. Sử dụng thuật toán phù hợp để tìm phần tử lớn thứ K trong mảng số nguyên đã cho.
//Ví dụ: Với Chuỗi “3, 1, 5, 12, 2, 11; 3” nghĩa là mảng [3, 1, 5, 12, 2, 11] và K = 3 -> cần tìm phần tử lớn thứ 3 là 5.
//c. Triệu gọi phương thức submitData để gửi kết quả phần tử lớn thứ K đã tìm được trở lại server.
//d. Kết thúc chương trình client.
import RMI.DataService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;
public class RMI_data {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService dataservice = (DataService) registry.lookup("RMIDataService");
        
        String studentCode="B21DCCN157";
        String qCode="Knb63v68";
        String receiveStr = (String)dataservice.requestData(studentCode, qCode);
        System.out.println("from server: "+ receiveStr);
        
        String[] parts = receiveStr.trim().split(";");
        String[] numberStr = parts[0].trim().split(", ");
        System.out.println("numberStr"+ numberStr);
        int[] numbers = new int[numberStr.length];
        for(int i = 0;i<numberStr.length;i++){
            numbers[i] = Integer.parseInt(numberStr[i]);
        }
        System.out.println("numbers: "+numbers);
        
        int k = Integer.parseInt(parts[1].trim());
        
        Arrays.sort(numbers);
        int ans= numbers[numbers.length - k];
        dataservice.submitData(studentCode, qCode, (Object) ans);
    
    }
}
