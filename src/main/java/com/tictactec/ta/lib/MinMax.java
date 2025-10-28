package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function MINMAX: Lowest and highest values over a specified period.
 */
public class MinMax {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outMin;
        public double[] outMax;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outMin = new double[allocationSize];
        double[] outMax = new double[allocationSize];
        int retCode = taLib.TA_MINMAX(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outMin, outMax);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outMin = outMin;
        result.outMax = outMax;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}