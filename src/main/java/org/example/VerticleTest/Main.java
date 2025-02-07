package org.example.VerticleTest;

import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // Deploy the worker verticle
        vertx.deployVerticle(new TestWorker());
    }
}
