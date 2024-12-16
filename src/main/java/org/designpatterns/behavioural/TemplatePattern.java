package org.designpatterns.behavioural;

abstract class PaymentFlow {

    public abstract void validateRequest();
    public abstract void calculateFees();
    public abstract void debitAmount();
    public abstract void creditAmount();

    public final void sendMoney() {
        validateRequest();
        calculateFees();
        debitAmount();
        creditAmount();
    }
}

class PayToFriend extends PaymentFlow {

    @Override
    public void validateRequest() {
        System.out.println("Friend : Validating Request");
    }

    @Override
    public void calculateFees() {
        System.out.println("Friend : Calculating Fees");
    }

    @Override
    public void debitAmount() {
        System.out.println("Friend : Debiting Amount");
    }

    @Override
    public void creditAmount() {
        System.out.println("Friend : Crediting Amount");
    }
}

class PayToMerchant extends PaymentFlow {

    @Override
    public void validateRequest() {
        System.out.println("Merchant : Validating Request");
    }

    @Override
    public void calculateFees() {
        System.out.println("Merchant : Calculating Fees");
    }

    @Override
    public void debitAmount() {
        System.out.println("Merchant : Debiting Amount");
    }

    @Override
    public void creditAmount() {
        System.out.println("Merchant : Crediting Amount");
    }
}

public class TemplatePattern {

    public static void main(String[] args) {
        PaymentFlow paymentFlow = new PayToFriend();
        paymentFlow.sendMoney();
    }
}
