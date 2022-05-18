package ca.jrvs.apps.twitter.utilities;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetCreator
{
  public static Tweet createTweet(String txt, double lat, double longi)
  {
    Tweet t = new Tweet();
    List<Double> coordList = new ArrayList<>();
    Coordinates coordObj = new Coordinates();

    coordList.add(Double.valueOf(lat));
    coordList.add(Double.valueOf(longi));
    t.setTxt(txt);
    coordObj.setCoords(coordList);
    t.setCoordinates(coordObj);
    return t;
  }
}
