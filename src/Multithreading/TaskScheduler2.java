package Multithreading;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler2 {
    int countTasks;
    ReentrantLock lock;
    Condition countUpdated;

    public TaskScheduler2() {
        countTasks = 0;
        lock = new ReentrantLock();
        countUpdated = lock.newCondition();
    }

    void schedule(Runnable task) {
        Thread thread = new Thread(() -> {
            lock.lock();
            try {
                countTasks++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            task.run();
            lock.lock();
            try {
                countTasks--;
                countUpdated.signalAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });
        thread.start();
    }

    void waitUntilComplete() {
        lock.lock();
        try {
            while (countTasks != 0) {
                countUpdated.await();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TaskScheduler2 taskScheduler = new TaskScheduler2();
        taskScheduler.schedule(() -> {
            System.out.println("task 1 started");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task 1 completed");
        });
        System.out.println("task 1 scheduled");
        taskScheduler.schedule(() -> {
            System.out.println("task 2 started");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task 2 completed");
        });
        System.out.println("task 2 scheduled");
        taskScheduler.waitUntilComplete();
        System.out.println("task 1&2 completed");
    }
}
