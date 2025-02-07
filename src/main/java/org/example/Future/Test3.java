package org.example.Future;

import io.vertx.core.AbstractVerticle;

public class Test3 extends AbstractVerticle {
    public void start(){

     vertx.executeBlocking(future -> {
         try {
             Thread.sleep(200);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         future.complete();
     }).onSuccess(res -> {
         System.out.println(Thread.currentThread().getName()+res+ " from the Test3");
     }).onFailure(res -> {
         System.out.println(Thread.currentThread().getName()+res+ " from the Test3");
     });
    }
}
