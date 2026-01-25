package Multithreading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultithreadedLRUCache {
    Map<Integer, Integer> cache;
    Map<Integer, ListNode> nodeMap;
    int capacity;
    DoublyLinkedList linkedList;
    ReentrantReadWriteLock readWriteLock;
    ExecutorService executorService;

    public MultithreadedLRUCache(int capacity) {
        cache = new HashMap<>();
        nodeMap = new HashMap<>();
        this.capacity = capacity;
        linkedList = new DoublyLinkedList();
        readWriteLock = new ReentrantReadWriteLock();
        executorService = Executors.newSingleThreadExecutor();
    }

    public int get(int key) {
        readWriteLock.readLock().lock();
        try {
            if (cache.containsKey(key)) {
                ListNode node = nodeMap.get(key);
                executorService.submit(() -> linkedList.removeNodeAndAddLast(node));
                return cache.get(key);
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(int key, int value) {
        readWriteLock.writeLock().lock();
        try {
            if (cache.containsKey(key)) {
                cache.put(key, value);
                ListNode node = nodeMap.get(key);
                linkedList.removeNodeAndAddLast(node);
            } else {
                if (cache.size() == capacity) {
                    ListNode head = linkedList.popFirst();
                    cache.remove(head.val);
                    nodeMap.remove(head.val);
                }
                ListNode node = new ListNode(key);
                nodeMap.put(key, node);
                linkedList.addLast(node);
                cache.put(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

class DoublyLinkedList {
    ListNode head;
    ListNode tail;
    int size;

    synchronized void addLast(ListNode node) {
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    synchronized void removeNodeAndAddLast(ListNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) head = node.next;
        if (node == tail) tail = node.prev;
        addLast(node);
    }


    synchronized ListNode popFirst() {
        if (size == 0) return null;
        ListNode node;
        if (size == 1) {
            node = head;
            head = null;
            tail = null;
        } else {
            node = head;
            head = head.next;
            head.prev = null;
        }
        size--;
        return node;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int val) {
        this.val = val;
    }
}
