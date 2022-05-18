package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;

public class containsDuplicate
{
  // can use a similar idea to find cycles
  public boolean hasDuplicate(int[] nums)
  {
      HashSet<Integer> dupeSet = new HashSet<>();
      for (int num : nums) {
        if (!dupeSet.add(num))
        {
          return true;
        }
      }
      return false;
  }
}
