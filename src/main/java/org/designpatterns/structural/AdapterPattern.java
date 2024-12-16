package org.designpatterns.structural;


interface WeightMachine {
    int getWeightInPounds();
}

class WeightMachineImpl implements WeightMachine {

    @Override
    public int getWeightInPounds() {
        return 30;
    }
}

interface WeightMachineAdapter{
    int getWeightInKg();
}

class WeightMachineAdapterImpl implements WeightMachineAdapter {

    WeightMachine weightMachine;

    WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }

    @Override
    public int getWeightInKg() {
        return weightMachine.getWeightInPounds()*10;
    }
}



public class AdapterPattern {

    public static void main(String[] args) {
        WeightMachine weightMachine = new WeightMachineImpl();
        WeightMachineAdapterImpl weightMachineAdapter = new WeightMachineAdapterImpl(weightMachine);

        System.out.println(weightMachineAdapter.getWeightInKg());
    }
}
