package org.designpatterns.structural;

interface EmployeeDao {
    void create();
}

class EmployeeDaoImpl implements EmployeeDao {

    public void create() {
        System.out.println("Employee has been created");
    }
}

class EmployeeDaoProxy implements EmployeeDao {

    String userType;
    EmployeeDao employeeDao;

    public EmployeeDaoProxy(String userType, EmployeeDaoImpl employeeDaoImpl) {
        this.userType = userType;
        this.employeeDao = employeeDaoImpl;
    }

    public void create() {

        if(userType == "ADMIN") {
            employeeDao.create();
        }else {
            System.out.println("Can not create employee");
        }
    }

}


public class ProxyPattern {

    public static void main(String[] args) {
        EmployeeDao employeeDaoProxy = new EmployeeDaoProxy("ADMIN", new EmployeeDaoImpl());
        employeeDaoProxy.create();
    }
}

