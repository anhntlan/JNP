/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less2_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author admin
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int SERVER_PORT = 1606;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server is listening on port " + SERVER_PORT);

            while (true) {
                // Chấp nhận kết nối từ client
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                socket.setSoTimeout(5000);

                // Tạo luồng ghi (BufferedWriter) và luồng đọc (BufferedReader)
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // Bước a: Nhận chuỗi từ client (mã sinh viên và mã câu hỏi)
                String receivedMessage = reader.readLine();
                System.out.println("Received from client: " + receivedMessage);

                // Bước b: Gửi chuỗi phản hồi lại cho client (chứa toàn ký tự thường)
                String serverResponse = "chuoinehe";
                writer.write(serverResponse);
                writer.newLine(); // Gửi dòng mới để client nhận biết kết thúc dòng
                writer.flush();
                System.out.println("Sent to client: " + serverResponse);

                // Bước c: Nhận chuỗi kết quả từ client (sau khi loại bỏ nguyên âm)
                String clientResult = reader.readLine();
                System.out.println("Received processed result from client: " + clientResult);

                // Đóng kết nối với client
                socket.close();
                System.out.println("Connection closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
