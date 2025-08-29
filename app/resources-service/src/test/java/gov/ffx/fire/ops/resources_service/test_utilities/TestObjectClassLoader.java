package gov.ffx.fire.ops.resources_service.test_utilities;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestObjectClassLoader {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T loadClassFromJson(String resourceJsonFile, Class<T> objectType) {

    T mappedObject = null;
    try (InputStream inputStream = objectType.getClassLoader().getResourceAsStream(resourceJsonFile)) {
      mappedObject =  objectMapper.readValue(inputStream, objectType);
      return mappedObject;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mappedObject;
  }
}
