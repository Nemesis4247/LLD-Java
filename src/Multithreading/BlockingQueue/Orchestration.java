package Multithreading.BlockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Orchestration {
    private Orchestration() {}
    public static void main(String[] args) throws InterruptedException {
        final int capacity = 5;
        final int totalItems = 20;
        final BlockingQueueUsingLock<Integer> queue = new BlockingQueueUsingLock<>(capacity);
//        final BlockingQueueUsingSemaphore<Integer> queue = new BlockingQueueUsingSemaphore<>(capacity);

        final AtomicInteger producedCount = new AtomicInteger(0);
        final AtomicInteger consumedCount = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Producer threads
        Runnable producer = () -> {
            for (int i = 0; i < totalItems / 2; i++) {
                try {
                    queue.produce(i);
                    producedCount.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Consumer threads
        Runnable consumer = () -> {
            for (int i = 0; i < totalItems / 2; i++) {
                try {
                    Integer val = queue.consume();
                    assert val != null : "Consumed null value!";
                    consumedCount.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Launch 2 producers and 2 consumers
        executor.execute(producer);
        executor.execute(producer);
        executor.execute(consumer);
        executor.execute(consumer);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        // Final assertions
        assert producedCount.get() == totalItems : "Mismatch in produced count!";
        assert consumedCount.get() == totalItems : "Mismatch in consumed count!";
        System.out.println("✅ All items produced and consumed successfully.");
    }
}
