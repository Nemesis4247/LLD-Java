package Multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {
    Queue<Integer> custQueue;
    ReentrantLock lock;
    Condition newCustomer;
    int seats;
    int numBarbers;
    boolean isShutdown;

    public BarberShop(int seats, int numBarbers) {
        custQueue = new LinkedList<>();
        lock = new ReentrantLock(true);
        newCustomer = lock.newCondition();
        this.seats = seats;
        isShutdown = false;
        this.numBarbers = numBarbers;
    }

    public static void main(String[] args) throws InterruptedException {
        BarberShop barberShop = new BarberShop(5, 2);
        barberShop.start();
        Thread.sleep(5000);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 7; i++) {
            int cust = i;
            executorService.submit(() -> barberShop.customerEnter(cust));
        }
        Thread.sleep(3000);
        for (int i = 7; i < 11; i++) {
            int cust = i;
            executorService.submit(() -> barberShop.customerEnter(cust));
        }
//        barberShop.shutdown();
        executorService.shutdown();
    }

    private void barber(int barberId) throws InterruptedException {
        while (!isShutdown) {
            lock.lock();
            int custId;
            try {
                while (custQueue.isEmpty()) {
                    System.out.println("Barber " + barberId + " waiting for customer");
                    newCustomer.await(); // sleep
                }
                custId = custQueue.poll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

            System.out.println("Customer " + custId + " got its turn via " + barberId + " !");
            Thread.sleep(1000); // working
            System.out.println("Customer " + custId + " is done !");
        }
    }

    public void customerEnter(int custId) {
        System.out.println("Customer " + custId + " enters !");
        lock.lock();
        try {
            if (custQueue.size() < seats) {
                custQueue.add(custId);
                newCustomer.signalAll();
                System.out.println("Customer " + custId + " seated in queue !");
            } else {
                System.out.println("Customer " + custId + " left, no seats available");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void start() {
        for (int i = 0; i < numBarbers; i++) {
            int barberId = i;
            Thread barber = new Thread(() -> {
                try {
                    this.barber(barberId);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            barber.start();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            isShutdown = true;
            newCustomer.signalAll(); // Wake up all barbers
        } finally {
            lock.unlock();
        }
    }
}
