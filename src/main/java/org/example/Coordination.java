package org.example;

import java.util.concurrent.CompletableFuture;

public class Coordination {
    public static void main(String[] args) throws Exception {

        CompletableFuture<Integer>f1=CompletableFuture.supplyAsync(()->2);
        CompletableFuture<Integer>f2=CompletableFuture.supplyAsync(()->4);

        // Wait for both futures to complete before proceeding
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(f1, f2);
        allOfFuture.get();  // Waits for both futures to finish

        // Now combine the results
        int result = f1.get() + f2.get();
        System.out.println("Final result after coordination: " + result);  // Outputs 8

    }
}
