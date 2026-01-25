package DesignFileSystem;

public class FileSystemService {
    String path;
    FileSystemManager fileSystemManager;

    public FileSystemService() {
        path = "";
        fileSystemManager = FileSystemManager.getInstance();
    }

    public void pwd() {
        System.out.println(path);
    }

    public void ls() {
        System.out.println(fileSystemManager.getContents(path));
    }

    public void cd(String arg) {
        if ("".equals(arg.split("/")[0])) {
            path = fileSystemManager.getOptimalPath(arg);
            System.out.println(fileSystemManager.getContents(arg));
        } else {
            path = fileSystemManager.getOptimalPath(path + "/" + arg);
            System.out.println(fileSystemManager.getContents(arg));
        }
    }

    public void mkdir(String dirName) {
        boolean isCreated = fileSystemManager.createDirectory(dirName, path);
        if (isCreated) System.out.println(dirName + " directory created successfully");
        else System.out.println(dirName + " directory creation failed");
    }

    public void touch(String fileName) {
        boolean isCreated = fileSystemManager.createFile(fileName, path);
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
        fs.ls();
    }
}
