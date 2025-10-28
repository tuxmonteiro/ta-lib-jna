package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function STOCH: Stochastic.
 */
public class Stoch {

    private static final Logger logger = LoggerFactory.getLogger(Stoch.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outSlowK;
        public double[] outSlowD;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, int optInFastKPeriod, int optInSlowKPeriod, int optInSlowKMA, int optInSlowDPeriod, int optInSlowDMA) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (high == null || high.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'high' is null or too small for endIdx=" + endIdx);
        }
        if (low == null || low.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'low' is null or too small for endIdx=" + endIdx);
        }
        if (close == null || close.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'close' is null or too small for endIdx=" + endIdx);
        }

        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outSlowK = new double[allocationSize];
        double[] outSlowD = new double[allocationSize];
        int retCode = taLib.TA_STOCH(startIdx, endIdx, high, low, close, optInFastKPeriod, optInSlowKPeriod, optInSlowKMA, optInSlowDPeriod, optInSlowDMA, outBegIdx, outNBElement, outSlowK, outSlowD);
        if (retCode != 0) {
            logger.error("TA-Lib function STOCH returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function STOCH returned error code: " + retCode);
        }
        result.outSlowK = outSlowK;
        result.outSlowD = outSlowD;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}