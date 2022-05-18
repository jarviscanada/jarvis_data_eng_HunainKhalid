package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utilities.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDao implements CrdDao<Tweet, String>
{
  //URL(URI) Constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  private final PercentEscaper pe = new PercentEscaper("",false);

  private URI getPostUri(Tweet t) throws URISyntaxException
  {
    List<Double> coords = t.getCoordinates().getCoordinates();
    return new URI(API_BASE_URI + POST_PATH + QUERY_SYM
    + "status" + EQUAL + pe.escape(t.getTxt())
    + AMPERSAND + "long" + EQUAL + pe.escape(String.valueOf(coords.get(0)))
    + AMPERSAND + "lat" + EQUAL + pe.escape(String.valueOf(coords.get(1))));
  }

  private Tweet parseRespBody( HttpResponse resp)
  {
    int sts = resp.getStatusLine().getStatusCode();
    if (sts != TwitterDao.HTTP_OK)
    {
      throw new RuntimeException("Unexpectted HTTP status:" + sts);
    }

    HttpEntity he = resp.getEntity();
    String str;

    if (he == null)
    {
      throw new RuntimeException("returned empty tweet-text");
    }
    else
    {
      try
      {
        str = EntityUtils.toString(he);
      }
      catch (IOException e)
      {
        throw new RuntimeException("Failed to convert tweet-text to string");
      }
    }

    try
    {
      return JsonUtil.toObjFromJson(str, Tweet.class);
    }
    catch (IOException e)
    {
      throw new RuntimeException("Cannot convert from JSON to Tweet object", e);
    }
  }

  @Autowired
  public TwitterDao(HttpHelper httpHelper)
  {
    this.httpHelper = httpHelper;
  }


  @Override
  public Tweet create(Tweet t)
  {
    URI uri;
    try
    {
      uri = getPostUri(t);
    }
    catch (URISyntaxException e)
    {
      throw new IllegalArgumentException("Usage: syntax exception : " + e);
    }
    HttpResponse resp = httpHelper.httpPost(uri);
    return parseRespBody(resp);
  }


  @Override
  public Tweet findById(String id)
  {
    URI uri;
    try
    {
      uri = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id"
      + EQUAL + id);
    }
    catch (URISyntaxException e)
    {
      throw new IllegalArgumentException("Tweet id incorrect/notfound" + e);
    }
    return parseRespBody(httpHelper.httpGet(uri));
  }

  @Override
  public Tweet deleteById(String id)
  {
    URI uri;
    try
    {
      uri = new URI(API_BASE_URI + DELETE_PATH + QUERY_SYM + "id"
          + EQUAL + id);
    }
    catch (URISyntaxException e)
    {
      throw new IllegalArgumentException("Tweet id incorrect/not-found" + e);
    }
    return parseRespBody(httpHelper.httpPost(uri));
  }


}
