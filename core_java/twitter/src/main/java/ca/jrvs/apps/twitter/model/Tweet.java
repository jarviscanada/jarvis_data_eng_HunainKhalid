package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet
{
  @JsonProperty("created_at")
  String createdAt;

  @JsonProperty("id")
  Long tweetID;

  @JsonProperty("id_str")
  String stringID;

  @JsonProperty("source")
  String src;

  @JsonProperty("text")
  String txt;

  @JsonProperty("coordinates")
  Coordinates coords;

  @JsonProperty("entities")
  Entities entities;

  @JsonProperty("retweet_count")
  int retweetCount;

  @JsonProperty("favorite_count")
  int favCount;

  @JsonProperty("favourited")
  boolean favorite;

  @JsonProperty("retweeted")
  boolean retweeted;

  public String getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(String createdAt)
  {
    this.createdAt = createdAt;
  }

  public Long getTweetID()
  {
    return tweetID;
  }

  public void setTweetID(Long tweetID)
  {
    this.tweetID = tweetID;
  }

  public String getStringID()
  {
    return stringID;
  }

  public void setStringID(String stringID)
  {
    this.stringID = stringID;
  }

  public String getSrc()
  {
    return src;
  }

  public void setSrc(String src)
  {
    this.src = src;
  }

  public String getTxt()
  {
    return txt;
  }

  public void setTxt(String txt)
  {
    this.txt = txt;
  }

  public Coordinates getCoordinates()
  {
    return coords;
  }

  public void setCoordinates(Coordinates coords)
  {
    this.coords = coords;
  }

  public Entities getEntities()
  {
    return entities;
  }

  public void setEntities(Entities entities)
  {
    this.entities = entities;
  }

  public int getRetweetCount()
  {
    return retweetCount;
  }

  public void setRetweetCount(int retweetCount)
  {
    this.retweetCount = retweetCount;
  }

  public int getFavCount()
  {
    return favCount;
  }

  public void setFavCount(int favCount)
  {
    this.favCount = favCount;
  }

  public boolean isFavorite()
  {
    return favorite;
  }

  public void setFavorite(boolean favorite)
  {
    this.favorite = favorite;
  }

  public boolean isRetweeted()
  {
    return retweeted;
  }

  public void setRetweeted(boolean retweeted)
  {
    this.retweeted = retweeted;
  }
}
