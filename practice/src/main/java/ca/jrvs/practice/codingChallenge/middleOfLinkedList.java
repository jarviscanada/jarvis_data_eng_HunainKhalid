package ca.jrvs.practice.codingChallenge;

public class middleOfLinkedList
{
  // This solution works by ending the loop
  // by going twice as fast (skip one node), hence
  // after the while loop breaks, slow has reached
  // n/2 nodes.
  public ListNode middleNode(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
