package MultipleLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shianhuang on 2/11/17.
 */
public class Worker {

    /*
        Lock explanation: For every class like Worker, there is only one lock. So if we use synchronized on both stageOne()
        and stageTwo(), the problem is that, only one thread can acquire the lock of Worker, other threads have to wait. But
        stageOne() and stageTwo() are independent, when one thread is accessing stageOne(), we shouldn't stop other threads to
        accessing stageTwo().

        So we create two instances lock1 and lock2, then use synchronized blocks instead of synchronized methods, this way when
        one thread is accessing stageOne(), other threads can access stageTwo().
     */
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void stageOne() {
        synchronized(lock1) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized(lock2) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time to execute: " + (end - start));
        System.out.println("List1: " + list1.size() + ", List2: " + list2.size());
    }
}
