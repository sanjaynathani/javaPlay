package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Threading {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //Thread odd = new Thread(new NumberPrinter("odd", lock, condition));
        //Thread even = new Thread(new NumberPrinter("even", lock, condition));

        Thread odd = new Thread(new Task(1, false, lock, condition));
        Thread even = new Thread(new Task(2, true, lock, condition));
        odd.start();
        even.start();
    }
}


class Task implements Runnable {

    private AtomicInteger counter;
    private static boolean locked;
    private boolean isOdd;

    private Lock lock;
    private Condition condition;

    public Task(int startsWith, boolean locked, Lock lock, Condition condition) {
        counter = new AtomicInteger(startsWith);
        //this.locked = locked;
        this.isOdd = startsWith == 1;

        this.lock = lock;
        this.condition = condition;
    }

    @Override public void run() {
        for(int i=0; i<5; i++) {
            if(isOdd)
                printOdd();
            else
                printEven();
        }
    }

    private synchronized  void print(){
        System.out.println(counter.getAndAdd(2));
        locked = true;
        notifyAll();
    }

    private void printOdd(){
        lock.lock();
            while((locked)) {
                try {
                    condition.await();
                }
                catch(InterruptedException ie) {

                }
            }
            System.out.println(counter.getAndAdd(2));
            locked = true;
        condition.signalAll();
        lock.unlock();
    }

    private void printEven(){
        lock.lock();
            while(!locked) {
                try {
                    condition.await();
                }
                catch(InterruptedException ie) {

                }
            }
            System.out.println(counter.getAndAdd(2));
            locked = false;
        condition.signalAll();
            lock.unlock();
    }
}


class NumberPrinter implements Runnable {
    private Lock lock;
    private Condition condition;
    private String type;
    private static boolean oddTurn = true;

    public NumberPrinter(String type, Lock lock, Condition condition) {
        this.type = type;
        this.lock = lock;
        this.condition = condition;

        //this.lock = new ReentrantLock();
        //this.condition = lock.newCondition();
    }

    public void run() {
        int i = type.equals("odd") ? 1 : 2;
        while (i <= 10) {
            if (type.equals("odd"))
                printOdd(i);
            if (type.equals("even"))
                printEven(i);
            i = i + 2;
        }
    }

    private void printOdd(int i) {
        // synchronized (lock) {
        lock.lock();
        if (!oddTurn) {
            try {
                // lock.wait();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(type + " " + i);
        oddTurn = false;
        // lock.notifyAll();
        condition.signalAll();
        lock.unlock();
    }

    // }

    private void printEven(int i) {
        // synchronized (lock) {
        lock.lock();
        if (oddTurn) {
            try {
                // lock.wait();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(type + " " + i);
        oddTurn = true;
        // lock.notifyAll();
        condition.signalAll();
        lock.unlock();
    }

    // }
}
