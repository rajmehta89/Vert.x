package org.example.VerticleTest;

import io.vertx.core.AbstractVerticle;

public class TestNormal extends AbstractVerticle {
    @Override
    public void start() {
        System.out.println(Thread.currentThread().getName() + " - Normal verticle started");
    }
}
