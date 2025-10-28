package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function MAXINDEX: Index of highest value over a specified period.
 */
public class MaxIndex {

    private static final Logger logger = LoggerFactory.getLogger(MaxIndex.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public int[] outInteger;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal == null || inreal.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal' is null or too small for endIdx=" + endIdx);
        }

        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        int[] outInteger = new int[allocationSize];
        int retCode = taLib.TA_MAXINDEX(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outInteger);
        if (retCode != 0) {
            logger.error("TA-Lib function MAXINDEX returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MAXINDEX returned error code: " + retCode);
        }
        result.outInteger = outInteger;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}