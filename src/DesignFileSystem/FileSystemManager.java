package DesignFileSystem;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileSystemManager {
    static volatile FileSystemManager instance;
    Directory root;
    ReentrantReadWriteLock readWriteLock;

    private FileSystemManager() {
        root = new Directory("");
        readWriteLock = new ReentrantReadWriteLock();
    }

    public static FileSystemManager getInstance() {
        if (instance == null) {
            synchronized (FileSystemManager.class) {
                if (instance == null)
                    instance = new FileSystemManager();
            }
        }
        return instance;
    }

    public Directory getRoot() {
        readWriteLock.readLock().lock();
        try {
            return this.root;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public boolean createDirectory(Directory node, String dirName) {
        readWriteLock.writeLock().lock();
        try {
            if (node == null) throw new NullPointerException("Arg Directory cannot be null");
            if (node.getChildren().containsKey(dirName)) return false;
            Directory newDir = new Directory(dirName, node);
            node.addNode(newDir);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public boolean createFile(Directory node, String fileName) {
        readWriteLock.writeLock().lock();
        try {
            if (node == null) return false;
            if (node.getChildren().containsKey(fileName)) return false;
            File file = new File(fileName);
            node.addNode(file);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public List<String> getContents(Directory dir) {
        readWriteLock.readLock().lock();
        try {
            if (dir == null) throw new NullPointerException("Arg Directory cannot be null");
            List<String> ans = new ArrayList<>();
            dir.getChildren().forEach((name, fs) -> {
                if (!name.equals(".") && !name.equals("..")) {
                    ans.add(fs.getName());
                }
            });
            return ans;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String getPath(Directory directory) {
        readWriteLock.readLock().lock();
        try {
            if (directory == null) throw new NullPointerException("Arg Directory cannot be null");
            StringBuilder ans = new StringBuilder();
            Directory curr = directory;
            while (!"".equals(curr.getName())) {
                ans.insert(0, "/" + curr.getName());
                curr = (Directory) curr.getChildren().get("..");
            }
            return ans.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Directory traverseFromNode(Directory directory, String path) {
        readWriteLock.readLock().lock();
        try {
            String[] nodes = path.split("/");
            Directory curr = directory;
            for (String node : nodes) {
                if (curr.getChildren().containsKey(node) && curr.getChildren().get(node).getType().equals(Type.DIR)) {
                    curr = (Directory) curr.getChildren().get(node);
                } else {
                    return null;
                }
            }
            return curr;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
