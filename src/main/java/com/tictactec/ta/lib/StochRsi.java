package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function STOCHRSI: Stochastic Relative Strength Index.
 */
public class StochRsi {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outFastK;
        public double[] outFastD;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod, int optInFastKPeriod, int optInFastDPeriod, int optInFastDMA) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outFastK = new double[allocationSize];
        double[] outFastD = new double[allocationSize];
        int retCode = taLib.TA_STOCHRSI(startIdx, endIdx, inreal, optInTimePeriod, optInFastKPeriod, optInFastDPeriod, optInFastDMA, outBegIdx, outNBElement, outFastK, outFastD);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outFastK = outFastK;
        result.outFastD = outFastD;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}