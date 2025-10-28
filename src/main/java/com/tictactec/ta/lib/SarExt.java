package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function SAREXT: Parabolic SAR - Extended.
 */
public class SarExt {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outReal;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double optInStartValue, double optInOffsetonReverse, double optInAFInitLong, double optInAFLong, double optInAFMaxLong, double optInAFInitShort, double optInAFShort, double optInAFMaxShort) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_SAREXT(startIdx, endIdx, high, low, optInStartValue, optInOffsetonReverse, optInAFInitLong, optInAFLong, optInAFMaxLong, optInAFInitShort, optInAFShort, optInAFMaxShort, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outReal = outReal;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}