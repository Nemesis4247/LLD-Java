package Multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ZeroEvenOdd {
    private final int n;
    private final Semaphore zeroTurn = new Semaphore(1);
    private final Semaphore oddTurn = new Semaphore(0);
    private final Semaphore evenTurn = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void printZero() {
        for (int i = 0; i < n; i++) {
            try {
                zeroTurn.acquire();  // Wait for turn
                System.out.print(0);
                if (i % 2 == 0) {
                    oddTurn.release(); // Signal odd
                } else {
                    evenTurn.release(); // Signal even
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printOdd() {
        for (int i = 1; i <= n; i += 2) {
            try {
                oddTurn.acquire();  // Wait for odd turn
                System.out.print(i);
                zeroTurn.release();  // Signal zero thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= n; i += 2) {
            try {
                evenTurn.acquire();  // Wait for even turn
                System.out.print(i);
                zeroTurn.release();  // Signal zero thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);

        Thread t1 = new Thread(zeroEvenOdd::printZero);
        Thread t2 = new Thread(zeroEvenOdd::printOdd);
        Thread t3 = new Thread(zeroEvenOdd::printEven);

        t1.start();
        t2.start();
        t3.start();
    }

    public void zeroEvenOdd(int n) {
        ReentrantLock lock = new ReentrantLock();
        Condition isZeroTurn = lock.newCondition();
        Condition isOddTurn = lock.newCondition();
        Condition isEvenTurn = lock.newCondition();
        AtomicInteger turn = new AtomicInteger(-1);
        AtomicInteger len = new AtomicInteger(0);

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> f2 = executorService.submit(() -> {
            lock.lock();
            try {
                int i = 0;
                while (len.get() < n) {
                    while (turn.get() != 1) {
                        isOddTurn.await();
                    }
                    System.out.print(2 * i++ + 1);
                    len.incrementAndGet();
                    turn.set(-2);
                    isZeroTurn.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        Future<?> f3 = executorService.submit(() -> {
            lock.lock();
            try {
                int i = 1;
                while (len.get() < n) {
                    while (turn.get() != 2) {
                        isEvenTurn.await();
                    }
                    System.out.print(2 * i++);
                    len.incrementAndGet();
                    turn.set(-1);
                    isZeroTurn.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        Future<?> f1 = executorService.submit(() -> {
            lock.lock();
            try {

                while (len.get() < n) {
                    while (turn.get() >= 0) {
                        isZeroTurn.await();
                    }
                    System.out.print(0);
                    len.incrementAndGet();
                    int newTurn = turn.accumulateAndGet(-1, (curr, x) -> curr * x);
                    if (newTurn == 1) {
                        isOddTurn.signal();
                    } else {
                        isEvenTurn.signal();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        try {
            f1.get();
            f2.get();
            f3.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

}