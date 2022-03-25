package ca.jrvs.practice.codingChallenge;

public class rotateString
{
  public boolean rotateString(String s, String goal)
  {
    /*
      At first was going to create new string permutations of
      all strings so O(n^2) possibilities in the
      search space,
      then another for-loop iterating and searching for a match
      between String s and String goal.

      This would have taken O(n^2) + O(n), O(n^2) time.
      The single pass solution appears below.

      Adding goal onto itself, yields xyzxyz, going through the
      list searching for s, which has all permutations
      and s goes through "goalgoal"
     */

   return s.length() == goal.length() && (goal + goal).contains(s);
  }

}
