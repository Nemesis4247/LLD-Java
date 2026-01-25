package Multithreading.UnixTail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;

public class Tail {
    String filePath;
    static int CHUNK_SIZE = 1024;

    public Tail(String path) {
        filePath = path;
    }

    public void printWithChunking(int num) throws IOException {
        StringBuilder ans = new StringBuilder();
        int lineCount = 0;
        try (RandomAccessFile filePtr = new RandomAccessFile(filePath, "r")) {
            long fileSize = filePtr.length();
            long pos = fileSize - CHUNK_SIZE;
            while (pos > -1 * CHUNK_SIZE) {
                long size = pos >= 0 ? CHUNK_SIZE : pos + CHUNK_SIZE;
                long start = Math.max(0, pos);
                byte[] data = new byte[(int) size];
                filePtr.seek(start);
                filePtr.readFully(data);
                for (int i=data.length-1; i>=0; i--) {
                    char c = (char) data[i];
                    if (c == '\n') {
                        lineCount++;
                    }
                    if (lineCount >= num) break;
                    ans.append((char) data[i]);
                }
                if (lineCount >= num) break;
                pos -= CHUNK_SIZE;
            }
        }
        ans.reverse();
        System.out.println(ans.toString());
    }

    public void tailOnline() throws IOException, InterruptedException {
        try (RandomAccessFile filePtr = new RandomAccessFile(filePath, "r")) {
            long fileSize = filePtr.length();
            filePtr.seek(fileSize);
            while (true) {
                long size = filePtr.length();
                if (size > fileSize) {
                    byte[] data = new byte[(int) (size-fileSize)];
                    filePtr.readFully(data);
                    System.out.print(new String(data, StandardCharsets.UTF_8));
                    filePtr.seek(size);
                    fileSize = size;
                }
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Tail tail = new Tail("C:\\test\\test.txt");
//        tail.printWithChunking(2);
        tail.tailOnline();
    }

}
