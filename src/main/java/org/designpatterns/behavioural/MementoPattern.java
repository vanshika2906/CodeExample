package org.designpatterns.behavioural;


import java.util.ArrayList;
import java.util.List;

class CareTaker {
    List<Momento> history = new ArrayList<>();
    
    public void addMomento(Momento momento) {
        history.add(momento);
    }
    
    public Momento undo() {
        if(!history.isEmpty()) {
            int lastIndex = history.size() - 1;

            Momento momento = history.get(lastIndex);
            history.remove(lastIndex);

            return momento;
        }
        return null;
    }
}


class Momento {

    int height;
    int width;

    Momento(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}


class Originator {
    int height;
    int width;

    Originator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Momento createMomento() {
        return new Momento(this.height, this.width);
    }

    public void restoreMomento(Momento restoredMomento) {
        this.height = restoredMomento.height;
        this.width = restoredMomento.width;
    }
}

public class MementoPattern {

    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        Originator originator = new Originator(10,20);

        Momento momento1 = originator.createMomento();

        careTaker.addMomento(momento1);

        originator.setHeight(20);
        originator.setWidth(30);

        originator.setHeight(30);
        originator.setWidth(40);

        Momento momento2 = originator.createMomento();
        careTaker.addMomento(momento2);

        originator.setHeight(40);
        originator.setWidth(50);

        Momento restoredMomento = careTaker.undo();
        originator.restoreMomento(restoredMomento);

        System.out.println(originator.height);
        System.out.println(originator.width);

        Momento restoredMomento2 = careTaker.undo();
        originator.restoreMomento(restoredMomento2);

        System.out.println(originator.height);
        System.out.println(originator.width);


    }
}
