package Multithreading;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UberRideProblem {
    ReentrantLock lock;
    Condition waitForTurn;
    int waitingDemocrats;
    int waitingRepublicans;
    Barrier barrier;

    public UberRideProblem() {
        lock = new ReentrantLock();
        waitForTurn = lock.newCondition();
        waitingDemocrats = 0;
        waitingRepublicans = 0;
        barrier = new Barrier();
    }

    public void requestSeat(Affiliation affiliation, String name) throws InterruptedException {
        System.out.println(name + "[" + affiliation.name() + "] waiting for turn");
        barrier.await(affiliation);

        System.out.println(name + " got the Uber");
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        UberRideProblem uber = new UberRideProblem();

        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Democrat, "Person 1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Republican, "Person 2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Republican, "Person 3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Democrat, "Person 4");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Democrat, "Person 5");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Democrat, "Person 6");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Democrat, "Person 7");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                uber.requestSeat(Affiliation.Democrat, "Person 8");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
    }
}

class Barrier {
    int[] waitingGroups;
    Semaphore[] semaphores;
    Semaphore mutex;

    public Barrier() {
        waitingGroups = new int[2];
        semaphores = new Semaphore[2];
        semaphores[0] = new Semaphore(0);
        semaphores[1] = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void await(Affiliation affiliation) {
        try {
            mutex.acquire();
            int count = ++waitingGroups[affiliation.getIndex()];
            if (count == 4) {
                semaphores[affiliation.getIndex()].release(4);
                waitingGroups[affiliation.getIndex()] -= 4;
            } else if ((count == 2 && waitingGroups[1-affiliation.getIndex()] == 2)) {
                semaphores[affiliation.getIndex()].release(2);
                semaphores[1-affiliation.getIndex()].release(2);
                waitingGroups[affiliation.getIndex()] -= 2;
                waitingGroups[1-affiliation.getIndex()] -= 2;
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong : ", e);
        } finally {
            mutex.release();
        }

        try {
            semaphores[affiliation.getIndex()].acquire();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong : ", e);
        }
    }
}

@Getter
enum Affiliation {
    Democrat(0),
    Republican(1);

    private final int index;

    Affiliation(int index) {
         this.index = index;
    }
}
