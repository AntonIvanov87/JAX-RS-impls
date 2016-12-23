import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JettyMain {

  static final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) throws Exception {
    Handler handler = new AbstractHandler() {
      @Override
      public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int[] ids = strsToInts(request.getParameterValues("id"));

        DTO[] dtos = DTOs.generate(request.getParameter("str"), ids);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), dtos);
        baseRequest.setHandled(true);
      }
    };
    JettyFactory.create(8081, handler).start();
  }

  public static int[] strsToInts(String[] strs) {
    int[] ints = new int[strs.length];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = Integer.parseInt(strs[i]);
    }
    return ints;
  }

  private JettyMain() {
  }
}
