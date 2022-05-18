package ca.jrvs.apps.twitter.model;

public class Hashtag
{
  int[] index;
  String hashTagName;

  public int[] getIndex()
  {
    return index;
  }

  public void setIndex(int[] index)
  {
    this.index = index;
  }

  public String getHashTagName()
  {
    return hashTagName;
  }

  public void setHashTagName(String hashTagName)
  {
    this.hashTagName = hashTagName;
  }
}
