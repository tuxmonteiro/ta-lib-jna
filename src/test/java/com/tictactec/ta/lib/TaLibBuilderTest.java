package com.tictactec.ta.lib;

import com.tictactec.ta.lib.results.RealResult;
import com.tictactec.ta.lib.results.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaLibBuilderTest {

    @Test
    public void testOneInArray() {
        TALibBuilder smaBuilder = new TALibBuilder(TaLibFunction.SMA);

        double[] values = {1,1,1,1,1,1,1,1,1};
        Result result = smaBuilder.startIdx(0).endIdx(values.length - 1).optInTimePeriod(3).inReal(values).execute();

        double[] expected = {1,1,1,1,1,1,1,0,0};
        assertInstanceOf(RealResult.class, result);
        assertArrayEquals(((RealResult)result).outReal(), expected);
        assertEquals(7, result.outNBElement());
    }
}
