import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.Servlet;

public class Jersey2Main {

  public static void main(String[] args) throws Exception {
    ResourceConfig resourceConfig = new ResourceConfig();
    resourceConfig.register(new HelloWorldResource());
    Servlet servlet = new ServletContainer(resourceConfig);
    JettyServletFactory.createServer(8081, servlet).start();
  }

  private Jersey2Main() {
  }
}
