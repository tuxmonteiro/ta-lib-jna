package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function MAMA: MESA Adaptive Moving Average.
 */
public class Mama {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outMAMA;
        public double[] outFAMA;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, double optInFastLimit, double optInSlowLimit) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outMAMA = new double[allocationSize];
        double[] outFAMA = new double[allocationSize];
        int retCode = taLib.TA_MAMA(startIdx, endIdx, inreal, optInFastLimit, optInSlowLimit, outBegIdx, outNBElement, outMAMA, outFAMA);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outMAMA = outMAMA;
        result.outFAMA = outFAMA;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}