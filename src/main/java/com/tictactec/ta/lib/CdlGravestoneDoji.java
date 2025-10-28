package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function CDLGRAVESTONEDOJI: Gravestone Doji.
 */
public class CdlGravestoneDoji {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public int[] outInteger;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] open, double[] high, double[] low, double[] close) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = open.length;
        int[] outInteger = new int[allocationSize];
        int retCode = taLib.TA_CDLGRAVESTONEDOJI(startIdx, endIdx, open, high, low, close, outBegIdx, outNBElement, outInteger);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outInteger = outInteger;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}