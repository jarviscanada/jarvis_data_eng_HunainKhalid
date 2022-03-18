package ca.jrvs.practice.codingChallenge;

public class fibonacciSequence
{
  /* O(2^n) : Each time a recursive call is made a copy
              of the original program is made, doubling
              time complexity by 2 each n times, hence
              O(2^n)
  */
  public int fibSeqRec(int num)
  {
    if (num == 0)
    {
        return 0;
    }
    else if (num == 1)
    {
      return 1;
    }
    else
    {
      return fibSeqRec(num - 1) + fibSeqRec(num - 2);
    }
  }

  /* O(n) : Trading space for improvement in time with
            memoization, by storing prior answers in an
            array and using them to calculate value at
            the current index. Since each index is
            reached at most once, the worst case scenario
            is O(n).
  */
  public int fibSeqDp(int num)
  {
    int[] mem = new int[num + 2];
    mem[0] = 0;
    mem[1] = 1;

    for (int i = 2; i <= num; i++)
    {
        mem[i] = mem[i - 1] + mem[i - 2];
    }
    return mem[num];
  }

}
