package DesignFileSystem;

import lombok.Setter;

public class File extends FileSystemNode {
    FileMetadata metadata;
    @Setter
    byte[] content;


    public File(String name) {
        super(name, Type.FILE);
        String[] fileWithExtension = name.split("\\.");
        String extension = fileWithExtension.length == 1 ? null : fileWithExtension[1];
        this.metadata = new FileMetadata(extension);
        content = new byte[0];
    }
}
