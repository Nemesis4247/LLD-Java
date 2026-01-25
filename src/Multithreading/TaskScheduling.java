package Multithreading;

import java.util.concurrent.Semaphore;

public class TaskScheduling {
    public static void main(String[] args) {
        int[][] arr = {
                {},
                {0},
                {0,1,3},
                {1,2}
        };
        TaskScheduling m = new TaskScheduling();
        m.solve(arr);
    }

    public void solve(int[][] arr) {
        int n = arr.length;
        Semaphore[] semaphores = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            semaphores[i] = new Semaphore(0);
        }
        for (int i = 0; i < n; i++) {
            int taskno = i;
            Thread ti = new Thread(() -> {
                try {
                    this.run(arr, taskno, semaphores);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            ti.start();

        }
    }

    private void run(int[][] arr, int taskno, Semaphore[] semaphores) throws InterruptedException {
        for (int i : arr[taskno]) {
            System.out.println(String.format("Task %d: waiting for resource %d", taskno, i));
            semaphores[i].acquire();
            System.out.println(String.format("Task %d: Acquired resource %d", taskno, i));
        }
        semaphores[taskno].release(arr.length);
        System.out.println(String.format("Task %d complete", taskno));
    }
}
