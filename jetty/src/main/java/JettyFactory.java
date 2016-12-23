import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;

public class JettyFactory {

  public static Server create(int port, Handler handler) {
    Server server = new Server(port);
    server.setHandler(handler);
    return server;
  }

  private JettyFactory() {
  }

}
