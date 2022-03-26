package ca.jrvs.practice.codingChallenge;

public class hasCycle
{
  // This solution works by ending the loop
  // by going twice as fast (skip one node), hence
  // after the while loop breaks, slow has reached
  // n/2 nodes.
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null)
    {
      return false;
    }
    ListNode p = head, q = head;
    while (q != null && q.next != null)
    {
      p = p.next;
      q = q.next.next;
      if (p == q)
      {
        return true;
      }
    }
    return false;
  }

}
