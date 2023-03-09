package org.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static BlockingQueue<String> arrayBlockingQueueA = new ArrayBlockingQueue<>(100);
    static BlockingQueue<String> arrayBlockingQueueB = new ArrayBlockingQueue<>(100);
    static BlockingQueue<String> arrayBlockingQueueC = new ArrayBlockingQueue<>(100);

    static final ExecutorService threadPool = Executors.newFixedThreadPool(java.lang.Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger maxA = new AtomicInteger();
        AtomicInteger maxB = new AtomicInteger();
        AtomicInteger maxC = new AtomicInteger();

        Thread two = new Thread(() -> {
            for (int i = 0; i <= 10_000; i++) {
                try {
                    String text = (String) threadPool.submit(new GenerateText()).get();
                    arrayBlockingQueueA.put(text);
                    arrayBlockingQueueB.put(text);
                    arrayBlockingQueueC.put(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread one = new Thread(() -> {
            for (int i = 0; i <= 10_000; i++) {
                try {
                    maxA.set(Math.max(maxA.get(), (int) threadPool.submit(new CheckA((String) arrayBlockingQueueA.take())).get()));
                    maxB.set(Math.max(maxB.get(), (int) threadPool.submit(new CheckA((String) arrayBlockingQueueB.take())).get()));
                    maxC.set(Math.max(maxC.get(), (int) threadPool.submit(new CheckA((String) arrayBlockingQueueC.take())).get()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        one.start();
        two.start();
        one.join();
        two.join();

        threadPool.shutdown();

        System.out.println("Max a is - " + maxA);
        System.out.println("Max b is - " + maxB);
        System.out.println("Max c is - " + maxC);
    }


}