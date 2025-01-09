package com.jarvis.backend;

import java.util.*;

public class Application {

    public static int longestSubstring(String str){

        if(str == null || str.isEmpty()) return 0;

        HashSet<Character> set = new HashSet<>();
        int max = 0;
        int left = 0;

        for(int right = 0; right < str.length(); right++) {
            while (set.contains(str.charAt(right))) {
                set.remove(str.charAt(left));
                left++;
            }
            set.add(str.charAt(right));
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println(longestSubstring("abcabcbb"));



//        Longest Substring Without Repeating Characters
//        Description: Find the length of the longest substring without repeating characters.
//        Example:
//        Input: "abcabcbb"
//        Output: 3 ("abc")

//        Word Break
//        Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
//                Note that the same word in the dictionary may be reused multiple times in the segmentation.
//                Example 1:
//        Input: s = "leetcode", wordDict = ["leet","code"]
//        Output: true
//        Explanation: Return true because "leetcode" can be segmented as "leet code".
//
//                Example 2:
//        Input: s = "applepenapple", wordDict = ["apple","pen"]
//        Output: true
//        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//                Note that you are allowed to reuse a dictionary word.
//
//                Example 3:
//        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//        Output: false


        String input = "catsandog";

        StringBuilder stringBuilder = new StringBuilder(input);

        List<String> words = Arrays.asList("cats","dog","sand","and","cat");

        List<String> dictionary = new ArrayList<>();

        for (int i = 0; i < stringBuilder.length(); ) {
            for(String word: words){
                if(stringBuilder.length() >= i + word.length()){
                    if(stringBuilder.substring(i, i + word.length()).equals(word)){
                        dictionary.add(stringBuilder.substring(i, i + word.length()));
                        stringBuilder.delete(i, i + word.length());
                    }
                }else{
                    i++;
                }
            }
        }

        for (String s : dictionary) {
            System.out.println(s);
            for(String word: words){

            }
        }
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