package ca.jrvs.apps.twitter.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtil
{
  public static String toJson(Object obj, boolean betterJSON,
      boolean incNullVals) throws JsonProcessingException
  {
    ObjectMapper map = new ObjectMapper();

    if(!incNullVals)
    {
      map.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    if (betterJSON)
    {
      map.enable(SerializationFeature.INDENT_OUTPUT);
    }

    return map.writeValueAsString(obj);
  }


  @SuppressWarnings("unchecked")
  public static <T> T toObjFromJson(String json, Class<?> cls) throws IOException
  {
    ObjectMapper map = new ObjectMapper();
    map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return (T) map.readValue(json, cls);

  }
}
