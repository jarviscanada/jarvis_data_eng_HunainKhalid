package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
/* This solution ignores pop/top O(n) solution
   and uses only 1 Linked-List as a queue
   representation, rather than 2.

   Methods
   * top(), pop(), empty() == O(1)
   * push() == O(n)
* */
public class MyStack
{
  private LinkedList<Integer> queue;

  public MyStack()
  {
   queue = new LinkedList<>();
  }

  public void push(int x)
  {
    queue.add(x);
    for (int i = 0; i < queue.size() - 1; i++)
    {
      queue.add(queue.poll());
    }
  }

  public int pop()
  {
    return queue.pop();
  }

  public int top()
  {
    return (queue.peek()).intValue();
  }

  public boolean empty()
  {
    return queue.isEmpty();
  }
}
