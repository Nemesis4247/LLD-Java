package Multithreading.BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueUsingLock<T> {
    Object[] arr;
    int head;
    int tail;
    int n;
    int size;
    ReentrantLock lock;
    Condition isFull;
    Condition isEmpty;

    public BlockingQueueUsingLock(int n) {
        this.n = n;
        size = 0;
        arr = new Object[n];
        head = -1;
        tail = -1;
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
    }

    public void produce(T e) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (size() == n) {
                isFull.await();
            }
            tail = (tail + 1) % n;
            if (head == -1) head = tail;
            arr[tail] = e;
            size++;
            isEmpty.signal();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        } finally {
            lock.unlock();
        }
    }

    public T consume() throws InterruptedException {
        try {
            lock.lockInterruptibly();
            while (size() == 0) {
                isEmpty.await();
            }
            T val = (T) arr[tail];
            if (size() == 1) {
                tail = -1;
                head = -1;
            } else {
                head = (head + 1) % n;
            }
            size--;
            isFull.signal();
            return val;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return size;
    }
}
