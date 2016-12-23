import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("/")
public class HelloWorldResource {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @GET
  @Path("string")
  @Produces(MediaType.TEXT_PLAIN)
  public String getString(@QueryParam("str") String str, @QueryParam("id") List<Integer> ids) {
    return str + ids + ThreadLocalRandom.current().nextInt();
  }

  @GET
  @Path("json")
  @Produces(MediaType.APPLICATION_JSON)
  public byte[] getJson(@QueryParam("str") String str, @QueryParam("id") List<Integer> ids) throws IOException {
    DTO[] dtos = DTOs.generate(str, ids.stream().mapToInt(id -> id).toArray());
    return objectMapper.writeValueAsBytes(dtos);
  }

}
