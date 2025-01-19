package com.jarvis.backend;

import java.util.LinkedHashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

    }
}


class WaitExample {
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

class JoinExample {
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
}

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    public V get(K key) {
        return cache.getOrDefault(key, null);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        System.out.println("Cache after adding 3 elements: " + lruCache);

        lruCache.get(1); // Accessing key 1
        lruCache.put(4, "D"); // Adding key 4, evicts least recently used key (2)
        System.out.println("Cache after adding 4th element: " + lruCache);

        lruCache.put(5, "E"); // Adding key 5, evicts least recently used key (3)
        System.out.println("Cache after adding 5th element: " + lruCache);
    }
}