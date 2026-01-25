package DesignFileSystem;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class Directory extends FileSystemNode {
    Map<String, FileSystemNode> children;

    public Directory(String name, Directory parent) {
        super(name, Type.DIR);
        this.children = new ConcurrentHashMap<>();
        children.put(".", this);
        children.put("..", parent);
    }

    public Directory(String name) {
        super(name, Type.DIR);
        this.children = new ConcurrentHashMap<>();
        children.put(".", this);
        children.put("..", this);
    }

    public void addNode(FileSystemNode node) {
        children.put(node.getName(), node);
    }
}
