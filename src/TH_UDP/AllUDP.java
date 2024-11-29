/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 * @author admin
 */
public class AllUDP {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        String student = "B21DCCN157;qs";
        byte[] datastu = student.getBytes();
        DatagramPacket stuPack = new DatagramPacket(datastu, datastu.length, InetAddress.getByName("17.233.312"), 2203);
        client.send(stuPack);

        byte[] recData = new byte[1024];
        DatagramPacket recPack = new DatagramPacket(recData, recData.length);
        //xu ly packet nhan ve
        // byte => String
        String recStr = new String(recPack.getData());
        String[] parts = recStr.split(";");
        String qsID = null;
        String[] numStr = parts[1].split(",");

        //mảng String => datatype: int, char
        String temp = String.join("", numStr);
        char[] charArray = temp.toCharArray();
        int[] numberAray = new int[numStr.length];

        int[] numbers = Arrays.stream(numStr).mapToInt(Integer::parseInt).toArray();
        // string => 
        int x = Integer.parseInt(qsID);
        char c = qsID.charAt(x);

        //nguoc lai
        String strFromCharArray = new String(charArray);

    }
}
