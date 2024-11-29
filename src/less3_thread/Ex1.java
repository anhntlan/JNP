/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less3_thread;

/**
 *
 * @author admin
 */
public class Ex1 {

    public static void main(String[] args) {
        new PrintNameE("A").start();
    }
}

class PrintNameE extends Thread {

    String Tname;

    public PrintNameE(String tName) {
        this.Tname = tName;
    }
@Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {

                Thread.sleep(1000);
                System.out.println(this.Tname);

            }
        }catch (InterruptedException e) {
            }
        

    }
}
