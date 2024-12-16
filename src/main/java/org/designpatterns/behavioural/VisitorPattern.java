package org.designpatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

interface FileSystemElement {
    void accept(Visitor visitor);
}


class File implements FileSystemElement {

    String fileName;
    int fileSize;

    File(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getName() {
        return fileName;
    }

    public int getSize() {
        return fileSize;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Directory implements FileSystemElement {
    String dirName;
    List<FileSystemElement> children;

    Directory(String name) {
        this.dirName = name;
        children = new ArrayList<>();
    }

    public void add(FileSystemElement fileSystemElement) {
        children.add(fileSystemElement);
    }

    public String getDirName() {
        return dirName;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for(FileSystemElement child : children) {
            child.accept(visitor);
        }
    }
}

interface Visitor {
    void visit(File file);
    void visit(Directory directory);
}

class SizeCalculator implements Visitor {
    int totalSize = 0;

    @Override
    public void visit(File file) {
        totalSize += file.fileSize;
    }

    @Override
    public void visit(Directory directory) {
    }

    public int getTotalSize() {
        return totalSize;
    }
}

class NamePrinter implements Visitor {

    @Override
    public void visit(File file) {
        System.out.println(file.getName());
    }

    @Override
    public void visit(Directory directory) {
        System.out.println(directory.getDirName());
    }
}


public class VisitorPattern {
    public static void main(String[] args) {
          Directory dir = new Directory("dir1");
          File file = new File("file1", 100);
          File file2 = new File("file2", 200);
          File file3 = new File("file3", 300);
          Directory subdir = new Directory("subdir");

          dir.add(file);
          dir.add(file2);
          subdir.add(file3);
          dir.add(subdir);

          SizeCalculator sizeCalculator = new SizeCalculator();
          NamePrinter namePrinter = new NamePrinter();
          dir.accept(sizeCalculator);
          dir.accept(namePrinter);
          System.out.println(sizeCalculator.getTotalSize());

    }
}
