package org.designpatterns.creational;


interface SMSNotification1 {
    void sendSMS(String message);
}

interface EmailNotification1 {
    void sendEmail(String message);
}

class BusinessSMSNotification implements SMSNotification1 {
    @Override
    public void sendSMS(String message) {
        System.out.println("[Business SMS] " + message);
    }
}

class BusinessEmailNotification implements EmailNotification1 {
    @Override
    public void sendEmail(String message) {
        System.out.println("[Business Email] " + message);
    }
}

class PersonalSMSNotification implements SMSNotification1 {
    @Override
    public void sendSMS(String message) {
        System.out.println("[Personal SMS] " + message);
    }
}

class PersonalEmailNotification implements EmailNotification1 {
    @Override
    public void sendEmail(String message) {
        System.out.println("[Personal Email] " + message);
    }
}

interface NotificationFactory1 {
    SMSNotification1 createSMSNotification();
    EmailNotification1 createEmailNotification();
}

class BusinessNotificationFactory implements NotificationFactory1 {
    @Override
    public SMSNotification1 createSMSNotification() {
        return new BusinessSMSNotification();
    }

    @Override
    public EmailNotification1 createEmailNotification() {
        return new BusinessEmailNotification();
    }
}

class PersonalNotificationFactory implements NotificationFactory1 {
    @Override
    public SMSNotification1 createSMSNotification() {
        return new PersonalSMSNotification();
    }

    @Override
    public EmailNotification1 createEmailNotification() {
        return new PersonalEmailNotification();
    }

}

class NotificationService {
    private SMSNotification1 smsNotification;
    private EmailNotification1 emailNotification;

    public NotificationService(NotificationFactory1 factory) {
        this.smsNotification = factory.createSMSNotification();
        this.emailNotification = factory.createEmailNotification();
    }

    public void sendSMS(String message) {
        smsNotification.sendSMS(message);
    }

    public void sendEmail(String message) {
        emailNotification.sendEmail(message);
    }
}


public class AbstractDesignPattern {

    public static void main(String[] args) {
        NotificationFactory1 businessFactory = new BusinessNotificationFactory();
        NotificationService notificationService = new NotificationService(businessFactory);

        notificationService.sendEmail("Hello");
        notificationService.sendSMS("Hello");

        NotificationFactory1 personalFactory = new PersonalNotificationFactory();
        notificationService = new NotificationService(personalFactory);
        notificationService.sendEmail("Hello");
        notificationService.sendSMS("Hello");
    }
}
