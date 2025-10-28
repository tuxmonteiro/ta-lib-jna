package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function STOCH: Stochastic.
 */
public class Stoch {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outSlowK;
        public double[] outSlowD;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, int optInFastKPeriod, int optInSlowKPeriod, int optInSlowKMA, int optInSlowDPeriod, int optInSlowDMA) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outSlowK = new double[allocationSize];
        double[] outSlowD = new double[allocationSize];
        int retCode = taLib.TA_STOCH(startIdx, endIdx, high, low, close, optInFastKPeriod, optInSlowKPeriod, optInSlowKMA, optInSlowDPeriod, optInSlowDMA, outBegIdx, outNBElement, outSlowK, outSlowD);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outSlowK = outSlowK;
        result.outSlowD = outSlowD;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}