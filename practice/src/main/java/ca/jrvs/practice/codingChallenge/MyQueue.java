package ca.jrvs.practice.codingChallenge;

import java.util.Stack;
/* AMORTIZED SOLUTION

*  push(), pop(), empty() = O(1)
*  peek() = Amortized O(1), O(n) when
            shifting values from stack1 to stack2
            whenever stack1 is full.
* */
public class MyQueue
{
  private Stack<Integer> stack1;
  private Stack<Integer> stack2;

  public MyQueue()
  {
    stack1 = new Stack<>();
    stack2 = new Stack<>();
  }

  public void push(int x)
  {
    stack1.add(x);
  }

  public int pop()
  {
    peek();
    return (stack2.pop()).intValue();
  }

  public int peek()
  {
    if (stack2.isEmpty())
    {
      while (!stack1.isEmpty())
      {
        stack2.push(stack1.pop());
      }
    }
    return stack2.peek();
  }

  public boolean empty()
  {
    return stack1.isEmpty() && stack2.empty();
  }

  /* BELOW IS MY ORIGINAL SOLUTION
     AND ATTEMPT

  private Stack<Integer> stack1;
  private Stack<Integer> stack2;

  public MyQueue()
  {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
  }

  public void push(int x)
  {
      int cap = stk.capacity();
      int size = stk.size();
      int diff = cap - size;

      if ((size + 1) > cap)
      {
        throw new RuntimeException("Queue capacity reached, cannot add more");
      }
      for (int i = -1; ++i < size;)
      {
        stk.add();
      }
      stk.add(x);

  }

  public int pop()
  {
    int res;
    if (!empty())
    {
      return (stk.pop()).intValue();
    }
    else
    {
      throw new RuntimeException("Cannot pop empty queue");
    }
  }

  public int peek()
  {
    return stk.peek();
  }

  public boolean empty()
  {
    return stk.empty();
  }

   */

}
