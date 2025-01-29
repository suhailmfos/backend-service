package com.jarvis.backend.app;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Create an ExecutorService using a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit a simple task (Runnable)
        executorService.execute(() -> {
            System.out.println("Task 1 is running - " + Thread.currentThread().getName());
        });

        // Submit a Callable task (task with return value)
        Future<String> result = executorService.submit(() -> {
            Thread.sleep(1000);
            return "Task 2 result - " + Thread.currentThread().getName();
        });

        // Retrieve the result of Callable
        System.out.println(result.get());  // This will block until the result is available

        // Submit another simple task (Runnable)
        executorService.execute(() ->
                System.out.println("Task 3 is running - " + Thread.currentThread().getName())
        );

        // Shutdown the ExecutorService
        executorService.shutdown();
    }
}
