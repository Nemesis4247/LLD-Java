package DesignFileSystem;

public class FileSystemService {
    FileSystemManager fileSystemManager;
    Directory directory;

    public FileSystemService() {
        fileSystemManager = FileSystemManager.getInstance();
        directory = fileSystemManager.getRoot();
    }

    public void pwd() {
        System.out.println(fileSystemManager.getPath(directory));
    }

    public void ls() {
        System.out.println(fileSystemManager.getContents(directory));
    }

    public void cd(String arg) {
        Directory dir;
        if ("".equals(arg.split("/")[0])) {
            dir = fileSystemManager.traverseFromNode(fileSystemManager.getRoot(), "." + arg);
        } else {
            dir = fileSystemManager.traverseFromNode(directory, arg);
        }
        if (dir == null) {
            System.out.println("Invalid path: " + arg);
            return;
        }
        this.directory = dir;
    }

    public void mkdir(String dirName) {
        boolean isCreated = fileSystemManager.createDirectory(directory, dirName);
        if (isCreated) System.out.println(dirName + " directory created successfully");
        else System.out.println(dirName + " directory creation failed");
    }

    public void touch(String fileName) {
        boolean isCreated = fileSystemManager.createFile(directory, fileName);
        if (isCreated) System.out.println(fileName + " file created successfully");
        else System.out.println(fileName + " file creation failed");
    }

    public static void main (String[] args) {
        FileSystemService fs = new FileSystemService();
        fs.mkdir("home");
        fs.cd("home");
        fs.mkdir("user");
        fs.cd("user");
        fs.touch("notes.txt");
        fs.ls(); // Should show: notes.txt
        fs.pwd(); // Should show: /home/user
        fs.cd("./../../home/../home/user/..");
        fs.pwd(); // Should show: /home
        fs.ls(); // Should show: user
    }
}
