package org.designpatterns.creational;


interface Clone {
    Clone clone();
}

class Employee implements Clone{
    public int age;
    public String name;
    public int employeeId;

    Employee(int age, String name, int employeeId) {
        this.age = age;
        this.name = name;
        this.employeeId = employeeId;
    }


    @Override
    public Clone clone() {
        return new Employee(age, name, employeeId);
    }
}

public class PrototypePattern {

    public static void main(String[] args) {
        Employee employee = new Employee(22, "Penny", 11813067);

        Employee clonedEmployee = (Employee) employee.clone();

        System.out.println(clonedEmployee.name);
    }
}
