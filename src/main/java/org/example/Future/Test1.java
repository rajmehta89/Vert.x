package org.example.Future;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class Test1  extends AbstractVerticle {

    public void start() {
        vertx.executeBlocking(promise -> {
            System.out.println(Thread.currentThread().getName() + " - Running in worker thread...");

            vertx.deployVerticle(new Test3(),res->{
                if(res.succeeded()){
                    System.out.println(Thread.currentThread().getName() + " - Deployed verticle");
                }

                else{
                    System.out.println(Thread.currentThread().getName() + " - Failed to deploy verticle");
                }
            });

            try {
                Thread.sleep(5000); // Simulating a blocking task
            } catch (InterruptedException e) {
                promise.fail(e);
                return;
            }
            promise.complete("Worker task completed");
        }, res -> {
            if (res.succeeded()) {
                System.out.println(Thread.currentThread().getName() + " - inside workerrerre rerreer Worker result: " + res.result());
            }
        });
    }

}
