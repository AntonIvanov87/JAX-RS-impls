import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import javax.servlet.Servlet;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class CXFMain {

  public static void main(String[] args) throws Exception {
    Servlet servlet = new CXFNonSpringJaxrsServlet(new MyApplication());
    JettyServletFactory.createServer(8081, servlet).start();
  }

  public static class MyApplication extends Application {
    @Override
    public Set<Object> getSingletons() {
      HashSet<Object> singletons = new HashSet<>();
      singletons.add(new HelloWorldResource());
      return singletons;
    }
  }

  private CXFMain() {
  }
}
