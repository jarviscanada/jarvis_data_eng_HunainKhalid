package ca.jrvs.practice.codingChallenge;

  public class removeNodeEndOfList
  {
    /* Good first attempt will need to
     *  revise the code and do dry runs
     * for understanding purposes.
     * */
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
      ListNode temp = new ListNode(0);
      ListNode node1 = temp, node2 = temp;
      node2.next = head;

      for (int i = 0; i < n + 1; i++)
      {
        node2 = node2.next;
      }

      while (node2 != null)
      {
        node1 = node1.next;
        node2 = node2.next;
      }

      node1.next = node1.next.next;
      return temp.next;
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }
  }

