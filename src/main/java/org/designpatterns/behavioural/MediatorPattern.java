package org.designpatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

interface AuctionMediator {
   void addBidder(Colleague colleague);
   void placeBid(Colleague colleague, int amount);
}

class Auction implements AuctionMediator {

    List<Colleague>  colleagues = new ArrayList<>();

    @Override
    public void addBidder(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void placeBid(Colleague colleague, int amount) {
        for(Colleague colleague1 : colleagues) {
            if(!colleague1.getName().equals(colleague.getName())) {
                colleague1.receivedNotification(amount);
            }
        }
    }
}

interface Colleague {

    void placeBid(int amount);
    void receivedNotification(int amount);

    String getName();
}

class Bidder implements Colleague {
     String name;
     AuctionMediator auctionMediator;

     Bidder(String name, AuctionMediator auctionMediator) {
         this.name = name;
         this.auctionMediator = auctionMediator;
         auctionMediator.addBidder(this);
     }

    @Override
    public void placeBid(int amount) {
        auctionMediator.placeBid(this, amount);
    }

    @Override
    public void receivedNotification(int amount) {
         System.out.println("Bidder " + name + " got notification, that someone has a places a bid of " + amount);
    }

    @Override
     public String getName() {
         return name;
     }
}

public class MediatorPattern {

    public static void main(String[] args) {
        AuctionMediator auctionMediator = new Auction();

        Colleague colleague = new Bidder("Stephen", auctionMediator);
        Colleague colleague2 = new Bidder("Sheldon", auctionMediator);
        Colleague colleague3 = new Bidder("Cooper", auctionMediator);

        colleague.placeBid(100);
        colleague2.placeBid(200);
        colleague3.placeBid(300);
    }
}
