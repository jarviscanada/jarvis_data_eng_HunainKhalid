package ca.jrvs.practice.codingChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class onlyDigits
{
  // using ascii
  public static boolean onlyDigits0(String str)
  {
    for (int i = 0; i < str.length(); i++)
    {
      int digit = (int) str.charAt(i);

      if (!(digit >= 49 && digit <= 57))
      {
        return false;
      }
    }
    return true;
  }


  // using java api, needs to throw numberformatexception
  // incorrect implementation so far, might need charisDigit
  /*public static boolean onlyDigits1(String str) throws NumberFormatException
  {
   Integer bool = Integer.parseInt(str);
   if (!(bool instanceof Integer))
   {
     return true;
   }
   return false;
  }*/

  //regex
  public static boolean onlyDigits2(String str)
  {
    String regx = "[0-9]+";

    Pattern p = Pattern.compile(regx);

    if (str == null)
    {
      return false;
    }
    Matcher m = p.matcher(str);

    return m.matches();
  }

  public static void main(String[] args)
  {
    // Method 0 - Output: [True, False, False]
    System.out.println(onlyDigits0("12345"));
    System.out.println(onlyDigits0("12.45"));
    System.out.println(onlyDigits0("123hello1235"));

    // Method 1 - Output: [True, Runtime number format exception]
    //System.out.println(onlyDigits1("12345"));
    //System.out.println(onlyDigits1("12345."));
    //System.out.println(onlyDigits1("12345hello"));

    // Method 2 - Output: [True, False, False]
    System.out.println(onlyDigits2("12345"));
    System.out.println(onlyDigits2("12345."));
    System.out.println(onlyDigits2("12345hello"));
  }

}
