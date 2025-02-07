package org.example.Future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class Test4 extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        try {
            System.out.println("Going to sleep - " + Thread.currentThread().getName());
            Thread.sleep(20000);
            System.out.println("Waking up - " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        startPromise.complete();
    }

    @Override
    public void stop() {
        System.out.println("Verticle stopped - " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        for (int i = 0; i < 22; i++) {
            vertx.deployVerticle(new Test4(), new DeploymentOptions().setWorker(true));
        }
    }
}
