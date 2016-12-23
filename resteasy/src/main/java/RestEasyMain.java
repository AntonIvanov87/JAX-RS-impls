import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class RestEasyMain {

  public static void main(String[] args) throws Exception {
    HttpServletDispatcher httpServletDispatcher = new HttpServletDispatcher();
    ServletHolder servletHolder = new ServletHolder("MainServlet", httpServletDispatcher);
    servletHolder.setInitParameter("javax.ws.rs.Application", "MyApplication");
    JettyServletFactory.createServer(8081, servletHolder).start();
  }

  private RestEasyMain() {
  }
}
