/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lesson7_RMI;

/**
 *
 * @author admin
 */
import java.net.MalformedURLException;
import java.rmi.*;
public class RMIClient {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        Calculator cal = (Calculator)
        Naming.lookup("rmi://localhost/cal16");
        System.out.println("SUM: "+cal.add(100,200));
    }
}
