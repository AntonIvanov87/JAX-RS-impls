import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

public class JettyServletFactory {

  public static Server createServer(int port, Servlet servlet) {
    ServletHolder servletHolder = new ServletHolder("MainServlet", servlet);
    return JettyFactory.create(port, createHandler(servletHolder));
  }

  public static Server createServer(int port, ServletHolder servletHolder) {
    return JettyFactory.create(port, createHandler(servletHolder));
  }

  private static Handler createHandler(ServletHolder servletHolder) {
    ServletContextHandler servletContextHandler = new ServletContextHandler();
    servletContextHandler.addServlet(servletHolder, "/*");
    return servletContextHandler;
  }

  private JettyServletFactory() {
  }

}
