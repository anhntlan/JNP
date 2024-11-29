/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lesson7_RMI;

/**
 *
 * @author admin
 */
import java.rmi.*;
 
public interface Calculator extends Remote {
    public int add(int a, int b);
    
}
