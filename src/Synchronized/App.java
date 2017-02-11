package Synchronized;

/**
 * Created by shianhuang on 2/10/17.
 */
public class App {
    private int count = 0;
    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    public synchronized void increment() {
        count++;
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment();
                    System.out.println("Thread1 is running!");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment();
                    System.out.println("Thread2 is running!");
                }
            }
        });

        t1.start();
        t2.start();

        // join() is used to make the thread finish its execution, otherwise, system will print out 0
        // because print count is in the mean thread, but t1 and t2 are running at different threads
        // printing out count is running at main thread, these three threads run parallelly
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The count is " + count);
    }
}
