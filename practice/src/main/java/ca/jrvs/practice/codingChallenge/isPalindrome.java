package ca.jrvs.practice.codingChallenge;

public class isPalindrome
{
  public boolean isPalindrome(String s)
  {
    /* The code snippet below would add
       unnecessary time converting the string
       from any case characters to lowercase
       ones adding another unneeded O(n) pass

      s = s.toLowerCase();
    */


    // ptr1, ptr2 are left and right pointer
    // respectively.
    int ptr1 = 0, ptr2 = s.length() - 1;

    while (ptr1 < ptr2)
    {
      while (ptr1 < ptr2 && !Character.isLetterOrDigit(s.charAt(ptr2)))
      {
        ptr2--;
      }
      while (ptr1 < ptr2 && !Character.isLetterOrDigit(s.charAt(ptr1)))
      {
        ptr1++;
      }
      if (Character.toLowerCase(s.charAt(ptr1++))
          != Character.toLowerCase(s.charAt(ptr2--)))
      {
        return false;
      }
    }
    return true;
  }



}
