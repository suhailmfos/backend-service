package com.jarvis.backend.app;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        // program a count the frequency of each character
        // using java stream

        String str = "Hello WorlD";

        java.util.Map<Character, Long> result = str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !Character.isWhitespace(c))
                .map(Character::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()));

        result.forEach((key, value) -> System.out.println(key + "-> " + value));
    }
}