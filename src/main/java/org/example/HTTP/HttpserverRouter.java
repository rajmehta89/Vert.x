package org.example.HTTP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HttpserverRouter extends AbstractVerticle {

    @Override
    public void start() {
        System.out.println("Creating HTTP server...");

        Router router = Router.router(vertx);

        router.route().pathRegex("/api/.*").handler(routingContext -> {
           System.out.println(routingContext.request().path());
        });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8084)
                .onComplete(result -> {
                    if (result.succeeded()) {
                        System.out.println("Server is listening on port 8084");
                    } else {
                        System.out.println("Failed to start server: " + result.cause());
                    }
                });
    }

    private void handleRequest(RoutingContext routingContext) {
        String name = routingContext.pathParam("name");
        int id = Integer.parseInt(routingContext.pathParam("id"));

        routingContext.response()
                .putHeader("Content-Type", "text/plain")
                .end("Received request: Name = " + name + ", ID = " + id);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpserverRouter()).onComplete(res -> {
            if (res.succeeded()) {
                System.out.println("Verticle deployed successfully");
            } else {
                System.out.println("Failed to deploy verticle");
                res.cause().printStackTrace();
            }
        });
    }
}
