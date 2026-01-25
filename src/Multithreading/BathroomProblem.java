package Multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BathroomProblem {
    ReentrantLock lock;
    int count = 0;
    int status = -1;
    Condition waitForTurn;
    int maxSize;

    public BathroomProblem(int maxSize) {
        lock = new ReentrantLock();
        this.maxSize = maxSize;
        waitForTurn = lock.newCondition();
    }

    public void enter(int groupId) throws InterruptedException {
        lock.lock();
        try {
            while (count != 0 && !(status == groupId && count < maxSize)) {
                waitForTurn.await();
            }
            status = groupId;
            count++;
            waitForTurn.signalAll();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong : " + e);
        } finally {
            lock.unlock();
        }

        Thread.sleep(TimeUnit.SECONDS.toMillis(10)); // Some work

        lock.lock();
        if (--count == 0) {
            status = -1;
        }
        waitForTurn.signalAll();
        lock.unlock();
    }


    public static void main(String[] args) {

    }
}
