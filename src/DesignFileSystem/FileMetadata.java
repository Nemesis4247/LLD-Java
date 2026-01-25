package DesignFileSystem;

public class FileMetadata {
    String extension;
    long sizeInBytes;
    String createdByUser;
    String updatedByUser;

    public FileMetadata(String extension, String user) {
        this.extension = extension;
        this.createdByUser = user;
        this.updatedByUser = user;
    }

    public FileMetadata(String extension) {
        this.extension = extension;
        this.createdByUser = "root";
        this.updatedByUser = "root";
    }
}
