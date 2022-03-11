package ca.jrvs.practice.codingChallenge;

public class fibonacciSequence
{
  /* O(2^n) : Each time a recursive call is made a copy
              of the original program is made,
  * */
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

  public int fibSeqDp(int num)
  {
    int[] mem = new int[num + 1];
    mem[0] = 0;
    mem[1] = 1;

    for (int i = 2; i <= num; i++)
    {
        mem[i] = mem[i - 1] + mem[i - 2];
    }
    return mem[num];
  }

}
