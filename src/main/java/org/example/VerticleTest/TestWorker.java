package org.example.VerticleTest;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class TestWorker extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        vertx.executeBlocking(promise -> {
            System.out.println(Thread.currentThread().getName() + " - Worker thread started");

            // Deploy a normal verticle from the worker thread
            vertx.deployVerticle(new TestNormal(), res -> {
                if (res.succeeded()) {
                    System.out.println(Thread.currentThread().getName() + " - Deployed normal verticle"+"in TestWorker  Normal verticle");
                } else {
                    System.out.println("Failed to deploy normal verticle: " + res.cause());
                }
            });

            promise.complete();
        });

        startPromise.complete();
    }
}
