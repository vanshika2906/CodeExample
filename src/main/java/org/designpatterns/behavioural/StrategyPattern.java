package org.designpatterns.behavioural;

interface PaymentStrategy {
    void pay();
}

class CardPaymentStrategy implements PaymentStrategy {

    private String cardNumber;

    CardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay() {
        System.out.println("Paying through card");
    }
}


class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Paying through cash");
    }
}

class ShoppingCart {

    private PaymentStrategy paymentStrategy;

    ShoppingCart(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout() {
        if(paymentStrategy==null) {
            System.out.println("No payment strategy update");
        }else {
            paymentStrategy.pay();
        }
    }
}


public class StrategyPattern {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new CardPaymentStrategy("123456789"));
        cart.checkout();

        ShoppingCart cart2 = new ShoppingCart(new CashPaymentStrategy());
        cart2.checkout();
    }
}
