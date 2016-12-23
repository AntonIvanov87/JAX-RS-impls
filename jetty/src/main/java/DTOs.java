import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DTOs {

  public static DTO[] generate(String str, int[] ids) {
    Random random = ThreadLocalRandom.current();

    DTO[] dtos = new DTO[ids.length];
    for (int i = 0; i < ids.length; i++) {
      DTO dto = new DTO();
      dto.id = ids[i];
      dto.str = str + random.nextInt();
      dtos[i] = dto;
    }
    return dtos;
  }

  private DTOs() {
  }
}
