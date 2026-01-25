package Multithreading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SnapshotMap<K, V> {
    private final HashMap<K, V> map = new HashMap<>();
    private final Map<Integer, Map<K, V>> snapshots = new HashMap<>();
    private int snapshotVersion = 0;
    private final ReentrantReadWriteLock readWriteLock1 = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock readWriteLock2 = new ReentrantReadWriteLock();

    public void put(K key, V value) {
        readWriteLock1.writeLock().lock();
        try {
            map.put(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock1.writeLock().unlock();
        }
    }

    public V get(K key) {
        readWriteLock1.readLock().lock();
        try {
            return map.get(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock1.readLock().unlock();
        }
    }

    public int takeSnapshot() {
        readWriteLock1.readLock().lock();
        readWriteLock2.writeLock().lock();
        try {
            int currentSnapshot = ++snapshotVersion;
            snapshots.put(currentSnapshot, new HashMap<>(map));
            return currentSnapshot;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock1.readLock().unlock();
            readWriteLock2.writeLock().unlock();
        }
    }

    public V getFromSnapshot(K key, int snapshotId) {
        readWriteLock2.readLock().lock();
        try {
            Map<K, V> snapshot = snapshots.get(snapshotId);
            return snapshot != null ? snapshot.get(key) : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock2.readLock().unlock();
        }
    }
}
