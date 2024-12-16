package org.designpatterns.structural;


interface IRobot {

    void display(int x, int y);
}

class HumanoidRobot implements IRobot {

    String type;
    String body;

    HumanoidRobot(String type, String body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("[HumanRobot] is displayed at " + x + "," + y);
    }
}

class DogRobot implements IRobot {

    String type;
    String body;

    DogRobot(String type, String body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("[DogRobot] is displayed at " + x + "," + y);
    }
}

public class FlyWeight {

    public static void main(String[] args) {

        IRobot robot1 = new HumanoidRobot("human", "metal");
        IRobot robot2 = new DogRobot("dog", "metal");

        robot1.display(0,0);
        robot1.display(1,1);

        robot2.display(2,2);
        robot2.display(3,3);
    }
}
