package DesignFileSystem;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class FileSystemNode {
    String name;
    Type type;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public FileSystemNode(String name, Type type) {
        this.name = name;
        this.type = type;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
