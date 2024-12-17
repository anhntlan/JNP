package FinalPractice;

//[Mã câu hỏi (qCode): BBLVK9GA].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
//Giao diện từ xa:
//public interface CharacterService extends Remote {
//public String requestCharacter(String studentCode, String qCode) throws RemoteException;
//public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
//}
//Trong đó:
//• Interface CharacterService được viết trong package RMI.
//• Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
//a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi số La Mã đầu vào".
//b. Thực hiện chuyển đổi chuỗi số La Mã nhận được thành số thập phân (Decimal).
//Quy tắc chuyển đổi: Các ký tự La Mã chính bao gồm: I=1, V=5, X=10, L=50, C=100, D=500, M=1000.
//    Ví dụ: "MCMXCIV" -> 1994.
//c. Triệu gọi phương thức submitCharacter để gửi số thập phân đã chuyển đổi trở lại server.
//d. Kết thúc chương trình client.
import RMI.CharacterService;
import java.rmi.*;
import java.rmi.registry.*;

public class RMI_Character {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        String studentCode = "B21DCCN157";
        String qCode = "BBLVK9GA";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");
        String receiveStr = characterService.requestCharacter(studentCode, qCode);
        System.out.println("from server: " + receiveStr);
        int decimal = romanToDecimal(receiveStr);
        String ans= String.valueOf(decimal);
        System.out.println("decimal= "+ans);        
        characterService.submitCharacter(studentCode, qCode, ans);
        

    }

    public static int romanToDecimal(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            int current = valueOfRoman(roman.charAt(i));
            int next = (i + 1 < roman.length()) ? valueOfRoman(roman.charAt(i + 1)) : 0;

            if (current < next) {
                result -= current;
            } else {
                result += current;
            }
        }
        return result;
    }

    public static int valueOfRoman(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
