package org.designpatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread {

    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}

class MyCallable implements Callable {

    @Override
    public String call() throws Exception {
        return "MyCallable";
    }
}

class MyCallable2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=1;i<100;i++) {
            sum+=i;
        }
        return sum;
    }
}

class Task implements Callable<String> {

    private final int id;
    Task(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "Task " + id;
    }
}

class Counter {
    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();

    public synchronized void increment() {
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public Singleton getInstance() {
        if(instance == null) {
            synchronized(Singleton.class) {
                if(instance== null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}


public class ThreadExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        MyThread thread2 = new MyThread();
        thread2.start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(new MyCallable2());
        //how to submit multiple callables
        System.out.println(future.get());
        executorService.shutdown();

        Thread thread3 = new Thread(new MyRunnable());
        thread3.start();


        ExecutorService executorService2 = Executors.newFixedThreadPool(3);

        List<Callable<String>> task = new ArrayList<>();
        for(int i=0;i<10;i++) {
            task.add(new Task(i));
        }

        List<Future<String>> futures = executorService2.invokeAll(task);

        for(Future<String> f : futures) {
            System.out.println(f.get());
        }

        executorService2.shutdown();


        Counter counter = new Counter();
        Thread thread4 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                counter.increment();
            }
        });

        Thread thread5 = new Thread(() -> {
            for(int i=0;i<10;i++) {
                counter.increment();
            }
        });

        thread4.start();
        thread5.start();

        try {
            thread4.join();
            thread5.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + counter.getCount());


        ConcurrentHashMap hashMap = new ConcurrentHashMap<>();
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    }
}

