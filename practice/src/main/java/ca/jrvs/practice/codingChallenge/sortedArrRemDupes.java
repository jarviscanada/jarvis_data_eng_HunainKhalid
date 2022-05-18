package ca.jrvs.practice.codingChallenge;

public class sortedArrRemDupes
{

  public int removeDuplicates(int[] nums)
  {
    int i = nums.length > 0 ? 1 : 0;

    for (int k : nums)
    {
      if (k > nums[i - 1])
      {
        nums[i++] = k;
      }
    }
    return i;
  }


}
