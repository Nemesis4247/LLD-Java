package Multithreading.BlockingQueue;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

public class BlockingQueueUsingSemaphore<T> {
    Deque<T> queue;
    Semaphore emptySlots;
    Semaphore filledSlots;

    public BlockingQueueUsingSemaphore(int capacity) {
        queue = new ConcurrentLinkedDeque<>();
        emptySlots = new Semaphore(capacity);
        filledSlots = new Semaphore(0);
    }

    public boolean produce(T e) throws InterruptedException {
        emptySlots.acquire();
        boolean result = queue.add(e);
        filledSlots.release();
        return result;
    }

    public T consume() throws InterruptedException {
        filledSlots.acquire();
        T e = queue.poll();
        emptySlots.release();
        return e;
    }
}
