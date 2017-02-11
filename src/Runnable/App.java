package Runnable;

/**
 * Created by shianhuang on 2/10/17.
 */
public class App {

    public static void main(String[] args) {
        Thread runner1 = new Thread(new Runner());
        Thread runner2 = new Thread(new Runner());

        runner1.start();
        runner2.start();
    }
}

class Runner implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
