package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function MINMAX: Lowest and highest values over a specified period.
 */
public class MinMax {

    private static final Logger logger = LoggerFactory.getLogger(MinMax.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outMin;
        public double[] outMax;
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
        double[] outMin = new double[allocationSize];
        double[] outMax = new double[allocationSize];
        int retCode = taLib.TA_MINMAX(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outMin, outMax);
        if (retCode != 0) {
            logger.error("TA-Lib function MINMAX returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MINMAX returned error code: " + retCode);
        }
        result.outMin = outMin;
        result.outMax = outMax;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}