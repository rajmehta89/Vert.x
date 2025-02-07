package org.example.Router;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import netscape.javascript.JSObject;


class Student{
    int marks;
    String name;

    Student(int marks, String name){
        this.marks = marks;
        this.name = name;
    }
}

public class Router1 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        Route route=router.route("/path/hello");

        route.handler(ctx->{
           System.out.println(ctx.request().path());
           ctx.response().setChunked(true);
           ctx.response().write("Hello World1");

           vertx.setTimer(1000, timer->{ctx.next();});
        });

        route.handler(ctx->{
           System.out.println(ctx.request().path());
           ctx.response().write("Hello World2");
           vertx.setTimer(1000, timer->{ctx.next();});
        });

        route.handler(ctx->{
            ctx.response().end("Hello World4");
           System.out.println(ctx.request().path());
        });


        router.get("/hy").respond(ctx-> Future.succeededFuture(new JsonObject().put("hello","world")));
        router.get("/object").respond(ctx-> Future.succeededFuture(Json.encode(new Student(100,"raj"))));


        vertx.createHttpServer().requestHandler(router).listen(8000).onComplete(ctx->{
            if(ctx.succeeded()){
                System.out.println("HTTP server started on port 8000");
            }
            else{
                System.out.println("HTTP server failed to start");
            }
        });
    }
}
