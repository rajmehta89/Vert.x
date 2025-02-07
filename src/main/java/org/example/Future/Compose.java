import io.vertx.core.*;

import java.util.Arrays;
import java.util.List;

public class Compose extends AbstractVerticle {

    public void start() {
        List<Future> futureList = Arrays.asList(
                firstTask(), secondTask(), thirdTask(),
                fourthTask(), fifthTask(), sixthTask(), seventhTask()
        );

        //all method example
        CompositeFuture.all(futureList)
                .onSuccess(res -> System.out.println("all All tasks completed successfully"))
                .onFailure(err -> System.out.println("all At least one task failed: " + err.getMessage()));

        //any method example
        CompositeFuture.any(futureList)
                .onComplete(res -> System.out.println("any All tasks completed successfully"))
                .onFailure(err -> System.out.println(" any At least one task failed: " + err.getMessage()));

        //join method example
        CompositeFuture.join(futureList)
                .onComplete(res -> System.out.println("join All tasks completed successfully"))
                .onFailure(err -> System.out.println("join At least one task failed: " + err.getMessage()));

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Compose(), res -> {
            if (res.succeeded()) {
                String deploymentId = res.result();
                System.out.println("Deployed: " + deploymentId);

                // Ensure tasks finish before undeploying
                vertx.setTimer(100, id -> {  // Wait before undeploying
                    vertx.undeploy(deploymentId).onSuccess(rs -> {
                        System.out.println("Undeployed " + deploymentId);

                        // Close Vert.x only after undeployment is successful
                        vertx.close().onComplete(c -> {
                            System.out.println("Closed " + deploymentId);
                        });

                    }).onFailure(err -> {
                        System.out.println("Undeploy failed: " + err.getMessage());
                    });
                });

            } else {
                System.out.println("Deployment failed: " + res.cause().getMessage());
            }
        });

        int defaultPoolSize = VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE;
        System.out.println("Default Event Loop Pool Size: " + defaultPoolSize);
    }


    public Future<String> firstTask() {
        return Future.succeededFuture("First Task Done");
    }

    public Future<String> secondTask() {
        return Future.succeededFuture("Second Task Done");
    }

    public Future<String> thirdTask() {
        return Future.failedFuture("third Task Failed");
    }

    public Future<String> fourthTask() {
        return Future.succeededFuture("Fourth Task Done");
    }

    public Future<String> fifthTask() {
        return Future.succeededFuture("Fifth Task Done");
    }

    public Future<String> sixthTask() {
        return Future.succeededFuture("Sixth Task Done");
    }

    public Future<String> seventhTask() {
        return Future.succeededFuture("Seventh Task Done");
    }
}
