import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

class TCPC extends AbstractVerticle {

    public void start() {
        NetClientOptions options = new NetClientOptions();

        NetClient client = vertx.createNetClient(options);
        client.connect(8000, "localhost", netSocketAsyncResult -> {
            if (netSocketAsyncResult.succeeded()) {
                NetSocket socket = netSocketAsyncResult.result();
                socket.write("Hello World");
                socket.handler(handler -> {
                    System.out.println("Handler received: " + handler.toString());
                });

                socket.closeHandler(handler -> {
                    System.out.println("Connection closed");
                });

                socket.exceptionHandler(handler -> {
                    System.out.println("Exception: " + handler.toString());
                });
            } else {
                System.out.println("Connection failed");
            }
        });
    }
}

public class TCPClient {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new TCPC());
    }
}
