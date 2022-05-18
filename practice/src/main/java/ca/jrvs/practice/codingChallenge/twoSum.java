package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

public class twoSum
{
  /*
  Created a method called twoSum slow, this
  solution is inefficient, passing through the
  same array twice, one pass of an array is always
  worst case O(n), iteration of an array in another
  array causes this solution to be worst-case O(n^2)
  */
  public int[] twoSum(int[] nums, int target)
  {
    int[] newArr = new int[2];

    for (int i = 0; i < nums.length; i++)
    {
      for (int j = 0; j < nums.length; j++)
      {
        if (i != j && nums[i] + nums[j] == target)
        {
          newArr[0] = i;
          newArr[1] = j;
        }
      }
    }
    return newArr;
  }

  /*
  To make this code O(n) use an auxiliary map
  to store differences ('target' value - current value)
  at the same time check if target - current[0] == (current[1])
  ,
  */
  public int[] twoSumFast(int[] nums, int target)
  {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++)
    {
      Integer difference = target - nums[i];

      if (map.containsKey(difference))
      {
        return new int[]{map.get(difference), i};
      }
      map.put(nums[i], i);
    }
  return null;
  }
}



