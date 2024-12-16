package org.designpatterns.structural;


class EmployeeFacade {
    EmployeeDao1 employeeDaoObj;

    EmployeeFacade() {
        this.employeeDaoObj = new EmployeeDao1();
    }

    void insert() {
        employeeDaoObj.insert();
    }

    void delete() {
        employeeDaoObj.delete();
    }

}

class EmployeeDao1 {

    void insert() {
        System.out.println("Employee has been added");
    }

    void delete() {
        System.out.println("Employee has been deleted");
    }
}



public class FacadePattern {

    public static void main(String[] args) {
        EmployeeFacade employeeFacade = new EmployeeFacade();

        employeeFacade.insert();
        employeeFacade.delete();
    }
}
