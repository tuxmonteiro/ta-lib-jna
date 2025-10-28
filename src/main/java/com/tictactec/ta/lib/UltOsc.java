package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function ULTOSC: Ultimate Oscillator.
 */
public class UltOsc {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outReal;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, int optInFirstPeriod, int optInSecondPeriod, int optInThirdPeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_ULTOSC(startIdx, endIdx, high, low, close, optInFirstPeriod, optInSecondPeriod, optInThirdPeriod, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outReal = outReal;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}