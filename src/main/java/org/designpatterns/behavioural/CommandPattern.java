package org.designpatterns.behavioural;


class AirConditioner {

    boolean isOn;
    int temperature;

    public void turnOnAc() {
        isOn = true;
        System.out.println("AC is turned on");
    }

    public void turnOffAc() {
        isOn = false;
        System.out.println("AC is turned off");
    }

    public void setTemperature(int temperature) {
        temperature = temperature;
        System.out.println("Temperature hase been set to" + temperature);
    }
}

interface ICommand {
    void execute();
}

class TurnOnCommand implements ICommand {

    AirConditioner ac;

    TurnOnCommand(AirConditioner ac) {
        this.ac = ac;
    }
    @Override
    public void execute() {
        ac.turnOnAc();
    }
}

class TurnOffCommand implements ICommand {

    AirConditioner ac;

    TurnOffCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOffAc();
    }
}

class SetTemperature implements ICommand {

    AirConditioner ac;
    int temperature;

    SetTemperature(AirConditioner ac, int temperature) {
        this.ac = ac;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        ac.setTemperature(temperature);
    }
}

class RemoteControl {
    ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }
    public void execute() {
        command.execute();
    }

}

public class CommandPattern {

    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(new TurnOnCommand(airConditioner));
        remoteControl.execute();

    }

}
