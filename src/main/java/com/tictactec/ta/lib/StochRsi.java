package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function STOCHRSI: Stochastic Relative Strength Index.
 */
public class StochRsi {

    private static final Logger logger = LoggerFactory.getLogger(StochRsi.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outFastK;
        public double[] outFastD;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod, int optInFastKPeriod, int optInFastDPeriod, int optInFastDMA) throws ArithmeticException, IndexOutOfBoundsException {
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
        double[] outFastK = new double[allocationSize];
        double[] outFastD = new double[allocationSize];
        int retCode = taLib.TA_STOCHRSI(startIdx, endIdx, inreal, optInTimePeriod, optInFastKPeriod, optInFastDPeriod, optInFastDMA, outBegIdx, outNBElement, outFastK, outFastD);
        if (retCode != 0) {
            logger.error("TA-Lib function STOCHRSI returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function STOCHRSI returned error code: " + retCode);
        }
        result.outFastK = outFastK;
        result.outFastD = outFastD;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}