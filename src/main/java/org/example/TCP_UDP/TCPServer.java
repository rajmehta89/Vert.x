import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

class TCPS extends AbstractVerticle {
    public void start() {
        NetServerOptions options = new NetServerOptions();

        // Create the NetServer
        NetServer server = vertx.createNetServer(options);

        // Handle incoming connections
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                String s = buffer.toString();
                System.out.println(s + " msg: is received here");
                socket.write("Read this data: " + s);
            });

            // Exception handler
            socket.exceptionHandler(t -> {
                t.printStackTrace();
            });

        });

        // Start the server and listen on port 8000
        server.listen(8000, "localhost", result -> {
            if (result.succeeded()) {
                System.out.println("Server listening on port 8000");
            } else {
                System.out.println("Server failed to listen on port 8000");
            }
        });
    }

    public void stop() {
        // Cleanup if necessary
    }
}

public class TCPServer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new TCPS());
    }
}
