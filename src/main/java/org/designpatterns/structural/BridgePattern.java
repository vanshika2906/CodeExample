package org.designpatterns.structural;

interface PaymentGateway {
    void processPayment(String amount);
}

class PayPalGateway implements PaymentGateway {
    @Override
    public void processPayment(String amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}

class StripeGateway implements PaymentGateway {
    @Override
    public void processPayment(String amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}

abstract class Payment {
    protected PaymentGateway paymentGateway;

    public Payment(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public abstract void pay(String amount);
}

class CreditCardPayment extends Payment {
    public CreditCardPayment(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }

    @Override
    public void pay(String amount) {
        System.out.println("Initiating Credit Card Payment...");
        paymentGateway.processPayment(amount);
    }
}

class UPIPayment extends Payment {
    public UPIPayment(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }

    @Override
    public void pay(String amount) {
        System.out.println("Initiating UPI Payment...");
        paymentGateway.processPayment(amount);
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        PaymentGateway paypal = new PayPalGateway();
        PaymentGateway stripe = new StripeGateway();

        Payment creditCardPayment = new CreditCardPayment(paypal);
        creditCardPayment.pay("100");

        Payment upiPayment = new UPIPayment(stripe);
        upiPayment.pay("200");

        Payment upiPaymentWithPayPal = new UPIPayment(paypal);
        upiPaymentWithPayPal.pay("300");
    }
}
