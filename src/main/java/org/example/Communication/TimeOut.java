package org.example.Communication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;


class ReciverVerticle extends AbstractVerticle {
    public void start() {

        vertx.eventBus().consumer("msg.cricket", msg->{
            vertx.setTimer(35000, timer->{
                System.out.println(msg.body());
                msg.reply("Reply from receiver: Received " + msg.body()); //
            });
        });
    }

    public void stop() {

    }
}


public class TimeOut {


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ReciverVerticle());

        vertx.eventBus().request("msg.cricket", "virat kohli!!",reply->{
            if(reply.succeeded()){
                System.out.println(reply.result().body());
            }
            else{
                System.out.println(reply.cause().getMessage());
            }
        } );



        Buffer buff = Buffer.buffer();

        buff.setInt(1000, 123);
        buff.setString(0, "hello");

        System.out.println(buff.length());


    }
}

