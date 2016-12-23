import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Jersey2NettyMain {

  public static void main(String[] args) {
    URI baseUri = UriBuilder.fromUri("http://localhost/").port(8081).build();
    ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
    NettyHttpContainerProvider.createServer(baseUri, resourceConfig, false);
  }

  private Jersey2NettyMain() {
  }
}
