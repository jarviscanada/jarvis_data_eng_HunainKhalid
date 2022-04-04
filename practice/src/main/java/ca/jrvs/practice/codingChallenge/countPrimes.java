package ca.jrvs.practice.codingChallenge;

public class countPrimes
{
  // The best way to count primes
  // is using the "Sieve of Eratosthenes"
  // method to efficiently count number
  // of primes to the end.
  // variations of this code below improve
  // average time but not big-O runtime
  public int countPrime(int n)
  {
    boolean[] isPrime = new boolean[n + 1];

    for (int i = 2; i < n; i++)
    {
      isPrime[i] = true;
    }

    for (int factor = 2; factor * factor <= n; factor++)
    {
      if (isPrime[factor])
      {
        for (int j = factor*2; j < n; j+=factor)
        {
          isPrime[j] = false;
        }
      }
    }

    int numPrimes = 0;
    for(int i = 2; i < n; i++)
    {
      if (isPrime[i])
      {
        numPrimes++;
      }
    }
    return numPrimes;
  }
}



