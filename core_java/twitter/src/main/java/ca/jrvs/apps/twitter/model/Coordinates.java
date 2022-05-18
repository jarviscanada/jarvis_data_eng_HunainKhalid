package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Coordinates
{
  @JsonProperty("coordinates")
  List<Double> coords;

  @JsonProperty("type")
  String type;

  public List<Double> getCoordinates()
  {
    return coords;
  }


  public void setCoords(List<Double> coords) {
    this.coords = coords;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
