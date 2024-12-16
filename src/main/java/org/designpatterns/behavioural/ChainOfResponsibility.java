package org.designpatterns.behavioural;


abstract class Logger {

    public static int INFO = 1;
    public static int ERROR = 3;
    public static int DEBUG = 2;

    protected int logLevel;
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if(this.logLevel == level) {
            write(message);
        }else if(nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);
}

class InfoLogger extends Logger {

    public InfoLogger() {
        this.logLevel = INFO;
    }
    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}

class ErrorLogger extends Logger {

    public ErrorLogger() {
        this.logLevel = ERROR;
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}

class DebugLogger extends Logger {

    public DebugLogger() {
        this.logLevel = DEBUG;
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}

class LoggerChain {
    public static Logger getLoggerChain() {
        // Create individual loggers
        Logger errorLogger = new ErrorLogger();
        Logger debugLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();

        // Build the chain: INFO -> DEBUG -> ERROR
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);

        return infoLogger;
    }
}

public class ChainOfResponsibility {

    public static void main(String[] args) {
         Logger loggerChain =  LoggerChain.getLoggerChain();
         loggerChain.logMessage(Logger.INFO, "This is a informational Message");
         loggerChain.logMessage(Logger.DEBUG, "This is a debug-level message");
         loggerChain.logMessage(Logger.ERROR, "This is a error-level message");
    }



}
