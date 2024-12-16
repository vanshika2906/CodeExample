package org.designpatterns.creational;

import java.util.*;

class Student {
    public String name;
    public int age;
    public List<String> subjects;

    public Student(StudentBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.subjects = builder.subjects;
    }

    public String toString() {
        return "Name :" + name + "Age :" + age + "Subjects : " + subjects.get(0) + subjects.get(1);
    }
}

abstract class StudentBuilder {
     public String name;
     public int age;
     public List<String> subjects = new ArrayList<>();

     public StudentBuilder setName(String name) {
         this.name = name;
         return this;
     }

     public StudentBuilder setAge(int age) {
         this.age = age;
         return this;
     }

     abstract StudentBuilder setSubjects();

     public Student build() {
         return new Student(this);
     }
}

class EngineeringStudent extends StudentBuilder {

    @Override
    StudentBuilder setSubjects() {
        subjects.add("DSA");
        subjects.add("CN");
        return this;
    }
}

class MBAStudent extends StudentBuilder {

    @Override
    StudentBuilder setSubjects() {
        subjects.add("Eco");
        subjects.add("Social");
        return this;
    }
}

class Director {

    StudentBuilder builder;

    Director(StudentBuilder studentBuilder) {
        this.builder = studentBuilder;
    }

    public Student createStudent() {
        return builder.setName("Penny").setAge(22).setSubjects().build();
    }
}

public class BuilderPattern {

    public static void main(String[] args) {
        Director obj1 = new Director(new EngineeringStudent());
        Director obj2 = new Director(new MBAStudent());

        System.out.println(obj1.createStudent().toString());
        System.out.println(obj2.createStudent().toString());
    }
}
