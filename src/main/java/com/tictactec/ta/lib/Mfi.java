package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function MFI: Money Flow Index.
 */
public class Mfi {

    private static final Logger logger = LoggerFactory.getLogger(Mfi.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outReal;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, double[] volume, int optInTimePeriod) throws ArithmeticException, IndexOutOfBoundsException {
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
        if (volume == null || volume.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'volume' is null or too small for endIdx=" + endIdx);
        }

        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_MFI(startIdx, endIdx, high, low, close, volume, optInTimePeriod, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function MFI returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MFI returned error code: " + retCode);
        }
        result.outReal = outReal;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}