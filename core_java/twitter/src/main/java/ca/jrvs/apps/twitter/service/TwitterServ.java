package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterServ implements Service
{
  private CrdDao crdDao;
  private static final int MAX_LENGTH = 140;
  private static final int MAX_LATITUDE = 90;
  private static final int MAX_LONGITUDE = 180;
  private static final int MIN_LATITUDE = -90;
  private static final int MIN_LONGITUDE = -180;

  @Autowired
  public TwitterServ(CrdDao crdDao)
  {
    this.crdDao = crdDao;
  }

  @Override
  public Tweet postTweet(Tweet tweet)
  {
    validPost(tweet);
    return (Tweet) crdDao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields)
  {
    validID(id);
    return (Tweet) crdDao.findById(id);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids)
  {
    validIDs(ids);
    List<Tweet> tweets = new ArrayList<>();

    for (String id : ids)
    {
      tweets.add((Tweet) crdDao.deleteById(id));
    }
    return tweets;
  }

  private void validID(String tweetID)
  {
    String regx = "\\d+";

    if (tweetID == null)
    {
      throw new NullPointerException("ID field cannot be empty.");
    }
    if (!tweetID.matches(regx))
    {
      throw new RuntimeException("Incorrect ID format, ID must match"
          + "regex pattern of '[0-9]+'");
    }
  }

  private void validPost(Tweet tweet)
  {
    int txtLen = tweet.getTxt().length();
    Double lat = tweet.getCoordinates().getCoordinates().get(0);
    Double longi = tweet.getCoordinates().getCoordinates().get(1);

   if (txtLen > MAX_LENGTH)
   {
     throw new RuntimeException("140 characters exceeded!");
   }
   if (lat < MIN_LATITUDE || lat > MAX_LATITUDE)
   {
     throw new RuntimeException("Latitude needs to be in range [-90,90]!");
   }
   if (longi < MIN_LONGITUDE || longi > MAX_LONGITUDE)
   {
     throw new RuntimeException("Longitude needs to be in range [-180,180]!");
   }
  }

  private void validIDs(String ...ids)
  {
    for (String id : ids)
    {
      validID(id);
    }
  }
}
