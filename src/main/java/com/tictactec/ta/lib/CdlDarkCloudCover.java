package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function CDLDARKCLOUDCOVER: Dark Cloud Cover.
 */
public class CdlDarkCloudCover {

    private static final Logger logger = LoggerFactory.getLogger(CdlDarkCloudCover.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public int[] outInteger;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] open, double[] high, double[] low, double[] close, double optInPenetration) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (open == null || open.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'open' is null or too small for endIdx=" + endIdx);
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
        int allocationSize = open.length;
        int[] outInteger = new int[allocationSize];
        int retCode = taLib.TA_CDLDARKCLOUDCOVER(startIdx, endIdx, open, high, low, close, optInPenetration, outBegIdx, outNBElement, outInteger);
        if (retCode != 0) {
            logger.error("TA-Lib function CDLDARKCLOUDCOVER returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function CDLDARKCLOUDCOVER returned error code: " + retCode);
        }
        result.outInteger = outInteger;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}