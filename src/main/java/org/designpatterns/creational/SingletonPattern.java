package org.designpatterns.creational;

//Eager Initiliasation
class DBConnection {
    private static DBConnection dbConnection = new DBConnection();

    private DBConnection() {

    }

    public DBConnection getInstance() {
        return dbConnection;
    }
}

//Lazy Initialisation
class DBConnection2 {
    private static DBConnection2 dbConnection;

    private DBConnection2() {

    }

    public DBConnection2 getInstance() {
        if(dbConnection == null) {
            dbConnection = new DBConnection2();
        }
        return dbConnection;
    }
}


//Synchronized
class DBConnection3 {
    private static DBConnection3 dbConnection;

    private DBConnection3() {

    }

    synchronized public DBConnection3 getInstance() {
        if(dbConnection == null) {
            dbConnection = new DBConnection3();
        }
        return dbConnection;
    }
}


//Double Locking
class DBConnection4 {
    private static volatile DBConnection4 dbConnection;

    private DBConnection4() {

    }

    synchronized public DBConnection4 getInstance() {
        if(dbConnection == null) {
            synchronized (DBConnection4.class) {
                if(dbConnection == null) {
                    dbConnection = new DBConnection4();
                }
            }
        }
        return dbConnection;
    }
}
public class SingletonPattern {
}
