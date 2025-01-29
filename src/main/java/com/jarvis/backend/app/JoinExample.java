package com.jarvis.backend.app;

public class JoinExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1: Starting...");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1: Completed.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2: Waiting for Thread 1 to finish...");
            try {
                t1.join(); // Wait for t1 to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2: Resuming after Thread 1 completion.");
        });

        t1.start();
        t2.start();
    }

    static class WaitExample {
        public static void main(String[] args) {
            Object lock = new Object();

            Thread producer = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("Producer: Producing data...");
                    try {
                        lock.wait(); // Waits until notified
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Producer: Resuming work after notification.");
                }
            });

            Thread consumer = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("Consumer: Processing data...");
                    lock.notify(); // Notifies waiting thread
                    System.out.println("Consumer: Notified producer.");
                }
            });

            producer.start();
            consumer.start();
        }
    }
}
