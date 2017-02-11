package Volatile;

import java.util.Scanner;

/**
 * Created by shianhuang on 2/10/17.
 */
public class App {
    public static void main(String[] args) {
        Processor proc = new Processor();
        proc.start();

        System.out.println("Press enter to stop....");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc.shutdown();
    }
}

class Processor extends Thread {

    private boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

