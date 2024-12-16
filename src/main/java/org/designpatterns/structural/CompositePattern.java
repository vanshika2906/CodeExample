package org.designpatterns.structural;


import java.util.ArrayList;
import java.util.*;

interface FileSystemEntity {
     void ls();
}

class File implements FileSystemEntity {

    String name;

    File(String name) {
        this.name = name;
    }
    @Override
    public void ls() {
       System.out.println("FileName : " + name);
    }
}

class Directory implements FileSystemEntity {

    List<FileSystemEntity> fileSystemEntityList = new ArrayList<>();

    String name;

    Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemEntity fileSystem) {
        fileSystemEntityList.add(fileSystem);
    }
    @Override
    public void ls() {
        System.out.println("Directory : " + name);
        for(FileSystemEntity fileSystem : fileSystemEntityList) {
            fileSystem.ls();
        }
    }
}

public class CompositePattern {

    public static void main(String[] args) {
        Directory dir1 = new Directory("dir1");
        File file1 = new File("file1");
        File file2 = new File("file2");
        File file3 = new File("file3");
        Directory subdir = new Directory("subdir");

        dir1.add(file1);
        dir1.add(file2);
        subdir.add(file3);
        dir1.add(subdir);

        dir1.ls();
    }


}
