package org.designpatterns.structural;


abstract class Notification {
    abstract void send(String message);
};

class EmailNotification extends Notification {

    @Override
    void send(String message) {
        System.out.println("Sending notification via email");
    }
}

abstract class DecoratorNotification extends Notification {
    abstract void send(String message);
}

class SMSDecorator extends DecoratorNotification {

    public Notification notification;

    public SMSDecorator(Notification notification) {
        this.notification = notification;
    }
    @Override
    void send(String message) {
        notification.send(message);
        System.out.println("Sending notification via SMS");
    }
}

class SlackDecorator extends DecoratorNotification {

    public Notification notification;

    public SlackDecorator(Notification notification) {
        this.notification = notification;
    }
    @Override
    void send(String message) {
        notification.send(message);
        System.out.println("Sending notification via Slack");
    }
}

public class DecoratorPattern {

    public static void main(String[] args) {
        Notification notification = new EmailNotification();

        Notification notification2  = new SMSDecorator(notification);
        Notification notification3 = new SlackDecorator(notification2);

        notification3.send("Hello");
    }
}
