package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function AROON: Aroon.
 */
public class Aroon {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outAroonDown;
        public double[] outAroonUp;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, int optInTimePeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outAroonDown = new double[allocationSize];
        double[] outAroonUp = new double[allocationSize];
        int retCode = taLib.TA_AROON(startIdx, endIdx, high, low, optInTimePeriod, outBegIdx, outNBElement, outAroonDown, outAroonUp);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outAroonDown = outAroonDown;
        result.outAroonUp = outAroonUp;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}