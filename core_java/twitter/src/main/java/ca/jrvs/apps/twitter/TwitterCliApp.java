package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterServ;
import ca.jrvs.apps.twitter.utilities.JsonUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterCliApp
{
  public static final String use = "UsageL TwitterCLIApp "
      + "post | show | delete [options]";

  private Controller controller;

  @Autowired
  public TwitterCliApp(Controller controller)
  {
    this.controller = controller;
  }

  public static void main(String[] args)
  {
     //Attain env variables and
    //create components to link.

    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(
        consumerKey,consumerSecret, accessToken, tokenSecret);

    CrdDao dao = new TwitterDao(httpHelper);
    Service serv = new TwitterServ(dao);
    Controller controller = new TwitterController(serv);
    TwitterCliApp app = new TwitterCliApp(controller);

    app.run(args);
  }
  public void run(String[] args)
  {
    if (args.length == 0)
    {
      throw new IllegalArgumentException("USAGE:");
    }

    if (args[0].toLowerCase().equals("post"))
    {
      controller.postTweet(args);
    }
    else if (args[0].toLowerCase().equals("show"))
    {
      controller.showTweet(args);
    }
    else if (args[0].toLowerCase().equals("delete"))
    {
      controller.deleteTweet(args).forEach(this::pTweet);
    }

  }

  private void pTweet(Tweet t)
  {
    try
    {
      System.out.println(JsonUtil.toJson(t, true, false));
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException("Cannot convert object");
    }
  }



}
