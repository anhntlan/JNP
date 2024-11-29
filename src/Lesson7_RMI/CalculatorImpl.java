/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lesson7_RMI;

/**
 *
 * @author admin
 */
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class CalculatorImpl  implements Calculator{
    @Override
    public int add(int a, int b) {
        return (a+b);
    }
    
}
