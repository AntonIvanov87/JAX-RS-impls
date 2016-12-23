import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JettyServletMain {

  static final ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) throws Exception {
    Servlet servlet = new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int[] ids = JettyMain.strsToInts(req.getParameterValues("id"));

        DTO[] dtos = DTOs.generate(req.getParameter("str"), ids);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");

        objectMapper.writeValue(resp.getOutputStream(), dtos);
      }

    };
    JettyServletFactory.createServer(8081, servlet).start();
  }

  private JettyServletMain() {
  }
}
