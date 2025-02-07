package org.example.Future;

import com.sun.source.tree.UsesTree;
import io.vertx.core.*;

public class Test extends AbstractVerticle {

//    public void start(Future<Void> startFuture) {
////        long currentTimeInSeconds = System.currentTimeMillis();
//        System.out.println(Thread.currentThread().getName()+"    "+currentTimeInSeconds);

//        vertx.setPeriodic(1000,l->{
//           System.out.println(System.currentTimeMillis()+"xyz");
//        });

//        vertx.setTimer(1000,l->{
//            System.out.println("timer over");
//        });

//        vertx.deployVerticle(new Test1(),new DeploymentOptions().setWorker(true),res->{
//            if(res.succeeded()){
//                System.out.println("verticle deployed");
//            }
//            else{
//                res.cause().printStackTrace();
//            }
//        });


//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println("start");
//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println("calling hello method");
//        hello().onComplete(r->{
//            if(r.succeeded()){
//                try {
//                    System.out.println(System.currentTimeMillis()/1000);
//                    System.out.println("promise completed sleeping for 2 second hello");
//                    Thread.sleep(4000);
//                    System.out.println(System.currentTimeMillis()/1000);
//                    System.out.println("sleep complete");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            else{
//                r.cause().printStackTrace();
//            }
//        });
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("calling hello2 method");
//        hello1().onComplete(r->{
//            if(r.succeeded()){
//                try {
//                    System.out.println("start time for hello sleep"+(System.currentTimeMillis()/1000));
//                    System.out.println("promise completed sleeping for 2 second hello1");
//                    Thread.sleep(3000);
//                    System.out.println("sleep complete");
//                    System.out.println("end time for hello sleep"+(System.currentTimeMillis()/1000));
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            else{
//                r.cause().printStackTrace();
//            }
//        });
//
//        System.out.println(System.currentTimeMillis()/1000);
//        System.out.println("end");
//
//        for(int i=0; i<100; i++){
//            System.out.println(System.currentTimeMillis()/1000+"           "+i);
//        }
//        startFuture.complete();
//
//    }

    @Override
    public void start(Promise<Void> startPromise) throws InterruptedException {


        System.out.println(Thread.currentThread().getName() + " - MainVerticle started");

        // Deploy WorkerVerticle as a worker thread
        vertx.deployVerticle(new Test1(), new DeploymentOptions(), res -> {
            if (res.succeeded()) {
                System.out.println(Thread.currentThread().getName()+"deployed worker verticle" + res.result());
            } else {
                System.out.println("Failed to deploy WorkerVerticle: " + res.cause());
            }
        });

        startPromise.complete();


//        vertx.close().onComplete(r->{
//            System.out.println("MainVerticle stopped");
//        }).onFailure(err->{
//           System.out.println("MainVerticle stopped");
//        });

    }



    public Future<String> hello(){
        Promise<String> promise = Promise.promise();
        vertx.setTimer(6000, id -> {
            promise.complete("hello");
        });

        return promise.future();
    }


    public Future<String> hello1(){
        Promise<String> promise = Promise.promise();
        vertx.setTimer(5000, id -> {
            promise.complete("hello1");
        });

        return promise.future();
    }


    public void stop() {
        System.out.println("stop");
    }
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Test());

    }
}