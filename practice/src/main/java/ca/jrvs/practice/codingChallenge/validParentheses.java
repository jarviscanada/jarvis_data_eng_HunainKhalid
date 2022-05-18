package ca.jrvs.practice.codingChallenge;
import java.util.Stack;

/* SOLUTION: "O(n)", can attempt to use less memory use
           for greater runtime.
* */
public class validParentheses
{
  private Stack<Character> stk = new Stack<>();
  private char[] chrs = {'(','{','[',')','}',']'};

  public boolean isValid(String s)
  {
    for (char c : s.toCharArray())
    {
      if (c == chrs[0])
      {
        stk.push(chrs[3]);
      }
      else if (c == chrs[1])
      {
        stk.push(chrs[4]);
      }
      else if (c == chrs[2])
      {
        stk.push(chrs[5]);
      }
      else if (stk.isEmpty() || stk.pop() != c)
      {
        return false;
      }
    }
    return stk.isEmpty();
  }
}
