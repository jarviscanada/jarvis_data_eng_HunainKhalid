package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class minmaxArray
{
  // using normal for-loop, O(n) runtime
  //              , O(1) space
  public static void min0(int[] arr)
  {
    int min = Integer.MAX_VALUE;

    for(int i = 0; i < arr.length; i++)
    {
      if (min > arr[i])
      {
        min = arr[i];
      }
    }

    System.out.println(min);
  }

  // using streams, O(n) runtime
  //              , O(1) space
  public static void min1(int[] arr)
  {
    int min = Arrays
        .stream(arr)
        .min()
        .orElseThrow(NoSuchElementException::new);

    System.out.println(min);
  }

  // using collections, runtime O(n),
  //                  , space O(1)
  public static void min2(int[] arr)
  {
    Arrays.sort(arr);
    System.out.println(arr[0]);
  }

  public static void main(String[] args) {
    int[] arr = {9, 10, 17, 33, 7, 5, 6, 11, 88, 66, 1};
    min0(arr);
    min1(arr);
    min2(arr);
  }
}
