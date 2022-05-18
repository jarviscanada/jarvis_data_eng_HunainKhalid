package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.utilities.TweetCreator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller
{
  private Service serv;
  private static final String COLON = ":";
  private static final String COMMA = ",";

  @Autowired
  public TwitterController(Service serv)
  {
    this.serv = serv;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3)
    {
      throw new IllegalArgumentException("Usage: TwitterCLIApp "
          + "post \"tweet text\" \"latitude:longitude\"");
    }
    String tweetTxt = args[1];
    String coords = args[2];
    String[] coorddArr = coords.split(COLON);

    if (coorddArr.length != 2 || StringUtils.isEmpty(tweetTxt))
    {
      throw new IllegalArgumentException("Invalid Location Information \n "
          + "Usage: TwitterCLIApp post \"tweet text\" \"latitude:longitude\"");
    }

    Double lat = null;
    Double longi = null;

    try
    {
      lat = Double.parseDouble(coorddArr[0]);
      longi = Double.parseDouble(coorddArr[1]);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("Invalid Location Type! \n "
          + "Usage: TwitterCLIApp post \"tweet text\" \"latitude:longitude\"", e);

    }

    Tweet t = TweetCreator.createTweet(tweetTxt, lat, longi);
    return serv.postTweet(t);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length < 2)
    {
      throw new IllegalArgumentException("Usage: TwitterCLIApp show "
          + "\"tweet id\" \"\"");
    }
    String tweetId = args[1];
    String[] fields = {};

    if (args.length > 2)
    {
      fields = args[2].split(COMMA);
    }
    return serv.showTweet(tweetId, fields);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length < 2)
    {
      throw new IllegalArgumentException("Usage: TwitterCLIApp delete "
      + "\"tweetid1,tweetid2,...\" \"\"");
    }

    String[] tweetIds = args[1].split(",");
    return serv.deleteTweets(tweetIds);
  }


}
