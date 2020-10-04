package com.example.elearningsystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private markscalculation markc;
    @Before
    public void setUp()
    {
        markc= new markscalculation();
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void calcResult()
    {
        float result = markc.calcResult(500,450);
        assertEquals(90,result,0.001);
    }
    @Test
    public void calcAvgmark()
    {
        float result = markc.calcAvgmark(450,5);
        assertEquals(90,result,0.001);
    }
}

