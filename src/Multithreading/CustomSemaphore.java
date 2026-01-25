package Multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomSemaphore {
    ReentrantLock lock;
    Condition waitForPermits;
    int size;

    public CustomSemaphore(int size) {
        this.size = size;
        lock = new ReentrantLock();
        waitForPermits = lock.newCondition();
    }

    void acquire() {
        lock.lock();
        try {
            while (size == 0) {
                waitForPermits.await();
            }
            size--;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    void release() {
        lock.lock();
        try {
            size++;
            waitForPermits.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
