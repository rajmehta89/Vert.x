package org.example.Worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import static java.lang.Thread.sleep;

public class WorkerThread extends AbstractVerticle {

    @Override
    public void start() {
        for (int i = 0; i < 5; i++) {  // Execute multiple blocking tasks
            final int taskId = i;
            vertx.executeBlocking(future -> {
                        try {
                            System.out.println("Executing Task " + taskId + " on Thread: " + Thread.currentThread().getName());
                            sleep(1000);
                            future.complete("Task " + taskId + " completed.");
                        } catch (Exception e) {
                            future.fail(e);
                        }
                    }).onSuccess(result -> System.out.println("Result: " + result))
                    .onFailure(err -> System.out.println("Error: " + err));
        }
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new WorkerThread(), new DeploymentOptions()
                .setWorker(true)
                .setWorkerPoolSize(5), result -> {
            if (result.failed()) {
                System.out.println("Deployment failed: " + result.cause());
            } else {
                System.out.println("Deployment successful: " + result.result());
            }
        });
    }
}
