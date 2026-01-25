package Multithreading;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduledExecutorServiceImpl {
    ReentrantLock lock;
    ExecutorService workers;
    PriorityQueue<ScheduledTask> taskQueue;
    Condition newTaskAdded;
    boolean isRunning;
    boolean isShutdown;

    public ScheduledExecutorServiceImpl(int poolSize) {
        lock = new ReentrantLock();
        workers = Executors.newFixedThreadPool(poolSize);
        taskQueue = new PriorityQueue<>();
        newTaskAdded = lock.newCondition();
        isRunning = false;
        isShutdown = false;
    }

    private void start() {
        lock.lock();
        try {
            isRunning = true;
            while (!isShutdown) {
                while (taskQueue.isEmpty()) {
                    newTaskAdded.await();
                }
                while (System.currentTimeMillis() < taskQueue.peek().getScheduledTime()) {
                    newTaskAdded.await(
                            taskQueue.peek().getScheduledTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                }
                if (isShutdown) break;
                ScheduledTask task = taskQueue.poll();
                switch (task.getTaskType()) {
                    case DELAY -> workers.submit(task.getCommand());
                    case FIXED_RATE -> {
                        workers.submit(task.getCommand());
                        task.scheduleNextIteration();
                        taskQueue.add(task);
                    }
                    case FIXED_DELAY -> workers.submit(() -> {
                        task.getCommand().run();
                        task.scheduleNextIteration();
                        taskQueue.add(task);
                    });
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
            isRunning = false;
        }

    }

    public void schedule(Runnable command, long delay, TimeUnit unit) {
        lock.lock();
        try {
            taskQueue.add(new ScheduledTask(command, TaskType.DELAY, delay, 0, unit));
            newTaskAdded.signalAll();
            if (!this.isRunning) {
                workers.submit(this::start);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong: " + e);
        } finally {
            lock.unlock();
        }
    }

    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        lock.lock();
        try {
            taskQueue.add(new ScheduledTask(command, TaskType.FIXED_RATE, initialDelay, period, unit));
            newTaskAdded.signalAll();
            if (!this.isRunning) {
                workers.submit(this::start);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong: " + e);
        } finally {
            lock.unlock();
        }
    }

    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        lock.lock();
        try {
            taskQueue.add(new ScheduledTask(command, TaskType.FIXED_DELAY, initialDelay, delay, unit));
            newTaskAdded.signalAll();
            if (!this.isRunning) {
                workers.submit(this::start);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong: " + e);
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            this.isShutdown = true;
            workers.shutdown();
            taskQueue.clear();
            this.isRunning = false;
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong: " + e);
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorServiceImpl service = new ScheduledExecutorServiceImpl(4);
        System.out.println("Curr time : " + LocalDateTime.now());
        service.schedule(() -> System.out.println("Task 1! Curr time: " + LocalDateTime.now()), 10, TimeUnit.SECONDS);
        Thread.sleep(TimeUnit.SECONDS.toMillis(15));
        service.scheduleAtFixedRate(() -> System.out.println("Task 2! Curr time: " + LocalDateTime.now()), 2, 3, TimeUnit.SECONDS);
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
        service.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 3! Curr time: " + LocalDateTime.now());
            }, 2, 3, TimeUnit.SECONDS);
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
        service.shutdown();
    }
}

@Getter
@AllArgsConstructor
class ScheduledTask implements Comparable<ScheduledTask> {
    Runnable command;
    TaskType taskType;
    @Setter
    long scheduledTime;
    long period;

    public ScheduledTask(Runnable command, TaskType taskType, long initialDelay, long period, TimeUnit unit) {
        this.scheduledTime = unit.toMillis(initialDelay) + System.currentTimeMillis();
        this.period = unit.toMillis(period);
        this.command = command;
        this.taskType = taskType;
    }

    public void scheduleNextIteration() {
        this.setScheduledTime(System.currentTimeMillis() + period);
    }

    @Override
    public int compareTo(ScheduledTask o) {
        return Long.compare(scheduledTime, o.scheduledTime);
    }
}

enum TaskType {
    DELAY,
    FIXED_RATE,
    FIXED_DELAY
}
