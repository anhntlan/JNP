/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Less7_RMI;

/**
 *
 * @author admin
 */
import java.net.MalformedURLException;
import java.rmi.*;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        
        CalculatorImpl cal = new CalculatorImpl();
        Naming.rebind("rmi://localhost/cal16",cal);
        System.out.println("cal rmi is litstening ");
    }
            
}
