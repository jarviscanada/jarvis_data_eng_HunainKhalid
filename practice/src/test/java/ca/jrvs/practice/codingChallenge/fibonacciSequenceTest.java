package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

public class fibonacciSequenceTest
{
  private fibonacciSequence fb;

  @Before
  public void setUp() {
    fb = new fibonacciSequence();
  }

  @Test
  public void recursive_imp_smallint()
  {
    fb.fibSeqRec(4);
  }

  @Test
  public void recursive_imp_medint()
  {
    fb.fibSeqRec(36);
  }

  @Test
  public void recursive_imp_largeint()
  {
    fb.fibSeqRec(44);
  }

  @Test
  public void dynaprg_imp_smallint()
  {
    fb.fibSeqDp(4);
  }

  @Test
  public void dynaprg_imp_medint()
  {
    fb.fibSeqDp(100000);
  }

  @Test
  public void dynaprg_imp_largeint()
  {
    fb.fibSeqDp(1000000);
  }
}