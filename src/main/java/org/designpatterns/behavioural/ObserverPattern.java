package org.designpatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

interface NotificationChannel {
    void notifyUser(String message);
}

class EmailNotification implements NotificationChannel {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("Email sent to " + email + ": " + message);
    }
}

interface NotificationService {
    void subscribe(NotificationChannel observer);
    void unsubscribe(NotificationChannel observer);
    void notifyAllUsers(String message);
}

class NotificationServiceImpl implements NotificationService {
    private List<NotificationChannel> observers;

    public NotificationServiceImpl() {
        this.observers =  new ArrayList<>();
    }

    @Override
    public void subscribe(NotificationChannel observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(NotificationChannel observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllUsers(String message) {
        for(NotificationChannel observer : observers) {
            observer.notifyUser(message);
        }
    }
}



public class ObserverPattern {
    public static void main(String[] args) {
        NotificationChannel channel = new EmailNotification("google.com");

        NotificationService notificationService = new NotificationServiceImpl();
        notificationService.subscribe(channel);
        notificationService.notifyAllUsers("Hello");
    }
}
