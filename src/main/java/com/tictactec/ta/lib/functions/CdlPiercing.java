package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;

/**
 * This class is a wrapper for the TA-Lib function CDLPIERCING: Piercing Pattern.
 */
public class CdlPiercing {

    private static final Logger logger = LoggerFactory.getLogger(CdlPiercing.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static Result execute(int startIdx, int endIdx, double[] open, double[] high, double[] low, double[] close) throws ArithmeticException, IndexOutOfBoundsException {
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

        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = open.length;
        int[] outInteger = new int[allocationSize];
        int retCode = taLib.TA_CDLPIERCING(startIdx, endIdx, open, high, low, close, outBegIdx, outNBElement, outInteger);
        if (retCode != 0) {
            logger.error("TA-Lib function CDLPIERCING returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function CDLPIERCING returned error code: " + retCode);
        }
        Result result = IntegerResult.builder()
            .outInteger(outInteger)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}