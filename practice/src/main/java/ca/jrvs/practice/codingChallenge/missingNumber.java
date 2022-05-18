package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

public class missingNumber
{
  // original for-loop idea
  // will not work on large numbers
  public static int missingNumber0(int[] nums)
  {
    int sum = 0;

    for (int i = 1; i <= nums.length; i++)
    {
      sum += i - nums[i - 1];
    }
    return sum;
  }

  //for-loop using sets,
  //will work for larger numbers
  // hashsets are not sorted or ordered,
  // hence faster than treesets
  public static int missingNumber1(int[] nums)
  {
    Set<Integer> s = new HashSet<>();
    for (int num : nums)
    {
      s.add(num);
    }

    int n = nums.length;
    for (int i = 0; i < n; i++)
    {
      if (!s.contains(i))
        return i;
    }
    return n;
  }

  // gaussian method,
  // will not work on large numbers
  public static int missingNumber2(int[] nums)
  {
    int n = nums.length;
    int total = n * (n + 1) / 2;
    int sum = 0;

    for (int i : nums)
    {
      sum += i;
    }
    return total - sum;
  }
}
