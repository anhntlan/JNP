/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_UDP;

import UDP.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 *
 * [Mã câu hỏi (qCode): LChX3IWq]. Thông tin sản phẩm vì một lý do nào đó đã bị
 * sửa đổi thành không đúng, cụ thể: a.	Tên sản phẩm bị đổi ngược từ đầu tiên và
 * từ cuối cùng, ví dụ: “lenovo thinkpad T520” bị chuyển thành “T520 thinkpad
 * lenovo” b.	Số lượng sản phẩm cũng bị đảo ngược giá trị, ví dụ từ 9981 thành
 * 1899
 *
 * Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209.
 * Yêu cầu là xây dựng một chương trình client giao tiếp với server để gửi/nhận
 * các sản phẩm theo mô tả dưới đây: a.	Đối tượng trao đổi là thể hiện của lớp
 * Product được mô tả như sau •	Tên đầy đủ của lớp: UDP.Product •	Các thuộc
 * tính: id String, code String, name String, quantity int •	Một hàm khởi tạo có
 * đầy đủ các thuộc tính được liệt kê ở trên •	Trường dữ liệu: private static
 * final long serialVersionUID = 20161107; b.	Giao tiếp với server theo kịch bản
 * • Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng
 * “;studentCode;qCode”. Ví dụ: “;B15DCCN001;EE29C059”
 *
 * •	Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại
 * chứa một đối tượng là thể hiện của lớp Product từ server. Trong đối tượng
 * này, các thuộc tính id, name và quantity đã được thiết lập giá trị. •	Sửa các
 * thông tin sai của đối tượng về tên và số lượng như mô tả ở trên và gửi đối
 * tượng vừa được sửa đổi lên server theo cấu trúc: 08 byte đầu chứa chuỗi
 * requestId và các byte còn lại chứa đối tượng Product đã được sửa đổi. •	Đóng
 * socket và kết thúc chương trình.
 */
//
// class Product implements Serializable {
//    private static final long serialVersionUID = 20161107;
//    
//    private String id;
//    private String code;
//    private String name;
//    private int quantity;
//
//    public Product (String id, String code, String name, int quantity) {
//        this.id = id;
//        this.code = code;
//        this.name = name;
//        this.quantity = quantity;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{id='" + id + "', code='" + code + "', name='" + name + "', quantity=" + quantity + "}";
//    }
//}
public class Obj_LChX3IWq {

    public static String reverseName(String name) {
        String[] words = name.split(" ");

        StringBuilder reversed = new StringBuilder();
        reversed.append(words[words.length - 1]);
        reversed.append(" ");

        for (int i = 1; i <= words.length - 2; i++) {
            reversed.append(words[i]);
            // Thêm dấu cách giữa các từ, trừ từ cuối cùng
            reversed.append(" ");

        }
        reversed.append(words[0]);

        // Trả về chuỗi đã đảo ngược
        return reversed.toString();
    }

    // Hàm đảo ngược giá trị số lượng sản phẩm
    public static int reverseQuantity(int quantity) {
        String quanStr = String.valueOf(quantity);
        String reverQuan = new StringBuilder(quanStr).reverse().toString();
        return Integer.parseInt(reverQuan);
//        String quantityStr = String.valueOf(quantity);
//        String reversedQuantityStr = new StringBuilder(quantityStr).reverse().toString();
//        return Integer.parseInt(reversedQuantityStr);
    }

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        DatagramSocket socket = new DatagramSocket();

        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int serverPort = 2209;

        String studentCode = "B21DCCN157";
        String questionCode = "LChX3IWq";
        String message = ";" + studentCode + ";" + questionCode;

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        socket.send(sendPacket);
        System.out.println("Đã gửi: " + message);

        // Nhận thông điệp từ server (08 byte đầu chứa requestId, các byte còn lại chứa đối tượng Product)
        byte[] receiveData = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        byte[] data = receivePacket.getData();

        String requestId = new String(data, 0, 8);
        System.out.println("Đã nhận requestId: " + requestId);

        ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, data.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product product = (Product) ois.readObject();
        System.out.println("Đã nhận đối tượng Product từ server: " + product);

        // Sửa thông tin tên và số lượng
        String correctedName = reverseName(product.getName());
        int correctedQuantity = reverseQuantity(product.getQuantity());
        product.setName(correctedName);
        product.setQuantity(correctedQuantity);
        System.out.println("Thông tin đã sửa: " + product);

        // Gửi lại đối tượng Product đã sửa cùng requestId
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(product);
        oos.flush();
        byte[] productData = baos.toByteArray();

        // Tạo mảng byte chứa requestId + Product
//        byte[] sendBackData = new byte[8 + productData.length];
//        System.arraycopy(requestId.getBytes(), 0, sendBackData, 0, 8);
//        System.arraycopy(productData, 0, sendBackData, 8, productData.length);
        // cach2  byteBuuffer
        ByteBuffer bytebuffer = ByteBuffer.allocate(8 + productData.length);
        bytebuffer.put(requestId.getBytes());
        bytebuffer.put(productData);
        byte[] sendBackData = bytebuffer.array();

        //Gửi lại dữ liệu
        DatagramPacket sendBackPacket = new DatagramPacket(sendBackData, sendBackData.length, serverAddress, serverPort);
        socket.send(sendBackPacket);
        System.out.println("Đã gửi lại đối tượng Product đã sửa");

    }
}
