/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package less3_thread;

/**
 *
 * @author admin
 */
public class Ex2 {

    public static void main(String[] args) {
        SharedData sd = new SharedData();
        new Thread(new Consumer(sd)).start();
        new Producer(sd).start();
    }
}

class Consumer implements Runnable {

    SharedData share;

    public Consumer(SharedData share) {
        this.share = share;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                share.comsume();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }
}

class Producer extends Thread {

    SharedData share;

    public Producer(SharedData share) {
        this.share = share;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                share.produce();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }
}

class SharedData {

    int data;
    boolean isProduce = false;

    public synchronized void produce() throws InterruptedException {
        if (isProduce) {
            this.wait();
        }
        this.data = (int) Math.round(Math.random() * 1000);
        System.out.println("Produce " + this.data);
        isProduce = true;
        this.notify();

    }

    public synchronized void comsume() throws InterruptedException {
        if (!isProduce) {
            this.wait();
        }
        System.out.println("consume " + this.data);
        this.data = 0;
        isProduce = false;
        this.notify();

    }
}
