package org.designpatterns.behavioural;

class VendingMachine {
    VendingState vendingState;

    public VendingState getVendingState() {
        return vendingState;
    }

    public void setVendingState(VendingState vendingState) {
        this.vendingState = vendingState;
    }
}

interface VendingState {

    void insertCoin(VendingMachine machine);

    void dispenseItem(VendingMachine machine);
}

class IdleState implements VendingState {

    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin inserted");
        machine.setVendingState(new WorkingState());
    }

    @Override
    public void dispenseItem(VendingMachine machine) {

    }
}

class WorkingState implements VendingState {

    @Override
    public void insertCoin(VendingMachine machine) {

    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Item Dispensed");
        machine.setVendingState(new IdleState());
    }
}

public class StatePattern {
    public static void main(String[] args) {
           VendingMachine machine = new VendingMachine();
           machine.setVendingState(new IdleState());

           machine.getVendingState().insertCoin(machine);
           machine.getVendingState().dispenseItem(machine);
    }
}
