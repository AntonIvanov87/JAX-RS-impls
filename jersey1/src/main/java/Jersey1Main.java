import com.sun.jersey.spi.container.servlet.ServletContainer;

import javax.servlet.Servlet;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Jersey1Main {

  public static void main(String[] args) throws Exception {
    Application application = new MyApplication();
    Servlet servlet = new ServletContainer(application);
    JettyServletFactory.createServer(8081, servlet).start();
  }

  static class MyApplication extends Application {
    @Override
    public Set<Object> getSingletons() {
      HashSet<Object> singletons = new HashSet<>();
      singletons.add(new HelloWorldResource());
      return singletons;
    }
  }

  private Jersey1Main() {
  }
}
