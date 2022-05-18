package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

public class longestSubstringNoRepeat
{
  public int lenLongSubstr(String s)
  {
    if (s == null)
    {
      throw new IllegalArgumentException("Input string is null");
    }

    int len = s.length();

    if (len <= 1)
    {
      return len;
    }

    HashMap<Character, Integer> map = new HashMap<>();
    int strt = 0;
    int subStrLen = 0;

    for (int end = 0; end < len; end++)
    {
      char c = s.charAt(end);
      if (map.containsKey(c))
      {
        strt = Math.max(strt, map.get(c) + 1);
      }
      map.put(c, end);
      subStrLen = Math.max(subStrLen, end - strt + 1);
    }
    return subStrLen;
  }
}
