package Multithreading.WebCrawler;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WebCrawlerBFS {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        ReentrantLock lock = new ReentrantLock();
        Queue<String> taskQueue = new LinkedList<>();
        Set<String> visited =  new HashSet<>();
        Condition newUrlAdded = lock.newCondition();
        long timeout = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        int NUM_THREADS = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        taskQueue.add(startUrl);
        for (int i=0; i<NUM_THREADS; i++) {
            executorService.submit(() ->
                    worker(taskQueue, htmlParser, lock, visited, newUrlAdded, timeout, unit));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(timeout*2, unit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return visited.stream().toList();
    }

    public void worker(Queue<String> taskQueue, HtmlParser htmlParser,
                       ReentrantLock lock, Set<String> visited,
                       Condition newUrlAdded, long timeout, TimeUnit unit) {
        while (true) {
            String node;
            lock.lock();
            try {
                while (taskQueue.isEmpty()) {
                    if (!newUrlAdded.await(timeout, unit)) return;
                }
                node = taskQueue.poll();
                visited.add(node);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            System.out.println(node);
            List<String> urls = htmlParser.getUrls(node);
            lock.lock();
            try {
                for (String url : urls) {
                    if (!visited.contains(url)) {
                        taskQueue.add(url);
                        newUrlAdded.signalAll();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
