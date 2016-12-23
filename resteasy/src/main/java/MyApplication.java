import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {
  @Override
  public Set<Object> getSingletons() {
    HashSet<Object> singletons = new HashSet<>();
    singletons.add(new HelloWorldResource());
    return singletons;
  }
}
