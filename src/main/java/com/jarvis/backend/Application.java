package com.jarvis.backend;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class Application {
    public static void main(String[] args) {

    }
}





































@Data
@AllArgsConstructor
class Employee {
    private String name;
    private String department;
}

@Data
@AllArgsConstructor
class Product {
    private String category;
    private double price;
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