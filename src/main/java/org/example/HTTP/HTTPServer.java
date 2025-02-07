package org.example.HTTP;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;



public class HTTPServer extends AbstractVerticle {
    @Override
    public void start() {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);

        // Define a simple route
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain")
                    .end("Hello, Vert.x!");
        });



    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HTTPServer());
    }
}
