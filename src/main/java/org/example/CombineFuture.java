package org.example;



import java.util.concurrent.*;


public class CombineFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer>future2=CompletableFuture.supplyAsync(()->2);

        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + result2);

        // Get the result after both futures complete
        System.out.println("Combined result: " + combinedFuture.get());  // Outputs 5

    }
}