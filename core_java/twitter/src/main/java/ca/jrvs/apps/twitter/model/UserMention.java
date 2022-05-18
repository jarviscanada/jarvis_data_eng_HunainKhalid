package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMention
{
  String screenName;
  String refUsr;
  int[] index;

  @JsonProperty("id")
  int usrID;

  @JsonProperty("id_str")
  String usrIdStr;

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public String getRefUsr() {
    return refUsr;
  }

  public void setRefUsr(String refUsr) {
    this.refUsr = refUsr;
  }

  public int[] getIndex() {
    return index;
  }

  public void setIndex(int[] index) {
    this.index = index;
  }

  public int getUsrID() {
    return usrID;
  }

  public void setUsrID(int usrID) {
    this.usrID = usrID;
  }

  public String getUsrIdStr() {
    return usrIdStr;
  }

  public void setUsrIdStr(String usrIdStr) {
    this.usrIdStr = usrIdStr;
  }
}
