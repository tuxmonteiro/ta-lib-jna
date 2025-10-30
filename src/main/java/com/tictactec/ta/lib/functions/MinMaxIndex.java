package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;

/**
 * This class is a wrapper for the TA-Lib function MINMAXINDEX: Indexes of lowest and highest values over a specified period.
 */
public class MinMaxIndex {

    private static final Logger logger = LoggerFactory.getLogger(MinMaxIndex.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal == null || inreal.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal' is null or too small for endIdx=" + endIdx);
        }

        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        int[] outMinIdx = new int[allocationSize];
        int[] outMaxIdx = new int[allocationSize];
        int retCode = taLib.TA_MINMAXINDEX(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outMinIdx, outMaxIdx);
        if (retCode != 0) {
            logger.error("TA-Lib function MINMAXINDEX returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MINMAXINDEX returned error code: " + retCode);
        }
        Result result = MinMaxIdxResult.builder()
            .outMinIdx(outMinIdx)
            .outMaxIdx(outMaxIdx)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}