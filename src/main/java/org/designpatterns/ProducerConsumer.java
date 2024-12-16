package org.designpatterns;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            for(int i=0;i<5;i++) {
                System.out.println("Produced: " + i);
                queue.put(i);
                Thread.sleep(500);
            }
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}


class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = queue.take();
                System.out.println("Consumed: " + value);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {

        BlockingQueue queue = new LinkedBlockingQueue<>(3);

        Thread t1 = new Thread(new Producer(queue));
        Thread t2 = new Thread(new Consumer(queue));

        t1.start();
        t2.start();
    }
}

