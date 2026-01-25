package DesignFileSystem;

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

    public boolean createDirectory(String dirName, String path) {
        readWriteLock.writeLock().lock();
        try {
            Directory node = traverse(path);
            if (node == null) return false;
            if (!node.getType().equals(Type.DIR)) return false;
            Directory newDir = new Directory(dirName, node);
            node.addNode(newDir);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public boolean createFile(String fileName, String path) {
        readWriteLock.writeLock().lock();
        try {
            Directory node = traverse(path);
            if (node == null) return false;
            if (!node.getType().equals(Type.DIR)) return false;
            File file = new File(fileName);
            node.addNode(file);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public List<String> getContents(String path) {
        readWriteLock.readLock().lock();
        try {
            Directory node = traverse(path);
            if (node == null) return Collections.emptyList();
            if (!node.getType().equals(Type.DIR)) return Collections.emptyList();
            List<String> ans = new ArrayList<>();
            for (String child : node.getChildren().keySet()) {
                if (!child.equals(".") && !child.equals("..")) {
                    ans.add(node.getChildren().get(child).getName());
                }
            }
            return ans;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String getOptimalPath(String path) {
        readWriteLock.readLock().lock();
        try {
            Directory node = traverse(path);
            if (node == null) return null;
            if (!node.getType().equals(Type.DIR)) return null;
            String ans = "";
            Directory curr = node;
            while (!"".equals(curr.getName())) {
                ans = "/" + curr.getName() + ans;
                curr = (Directory) curr.getChildren().get("..");
            }
            return ans;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    private Directory traverse(String path) {
        String[] nodes = path.split("/");
        Directory curr = root;
        for (int i = 1; i < nodes.length; i++) {
            if (curr.getChildren().containsKey(nodes[i]) && curr.getChildren().get(nodes[i]).getType().equals(Type.DIR)) {
                curr = (Directory) curr.getChildren().get(nodes[i]);
            } else {
                return null;
            }
        }
        return curr;
    }
}
