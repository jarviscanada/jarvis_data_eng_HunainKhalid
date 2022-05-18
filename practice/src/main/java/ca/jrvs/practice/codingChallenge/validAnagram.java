package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

public class validAnagram
{
  // Attempting O(n) + 26 solution
  public boolean isAnagram(String s, String t)
  {
      int[] totalChars = new int[26];
      if (s.length() != t.length())
      {
        return false;
      }
      for (int i = 0; i < s.length(); i++)
      {
        totalChars[s.charAt(i) - 97]++;
        totalChars[t.charAt(i) - 97]--;
      }
      for (int i = 0 ; i < 26; i++)
      {
        if (totalChars[i] != 0)
        {
          return false;
        }
      }
      return true;
  }
}
