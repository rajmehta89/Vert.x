package org.example.Communication;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;

import java.util.concurrent.Future;


class SenderVerticle extends AbstractVerticle {
    @Override
    public void start() {
        vertx.setPeriodic(2000, id -> { // Send message every 2 seconds
            vertx.eventBus().send("message.address", "Hello from SenderVerticle");
            System.out.println("Message sent!");
        });
    }
}


class ReceiverVerticle extends AbstractVerticle {
    public void start() {
        vertx.eventBus().consumer("message.address", (Message<String> message) -> {
            System.out.println("Received: " + message.body());
            message.reply("Message received!"); // Optional: Replying to sender
        });
    }
}


public class Communication {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Vertx vertx =Vertx.vertx();
        vertx.deployVerticle(new SenderVerticle());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vertx.deployVerticle(new ReceiverVerticle());
        long end=System.currentTimeMillis();
        System.out.println("Total time: " + (end-start));
    }
}