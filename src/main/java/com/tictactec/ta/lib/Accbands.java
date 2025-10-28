package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function ACCBANDS: Acceleration Bands.
 */
public class Accbands {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outRealUpperBand;
        public double[] outRealMiddleBand;
        public double[] outRealLowerBand;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, int optInTimePeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outRealUpperBand = new double[allocationSize];
        double[] outRealMiddleBand = new double[allocationSize];
        double[] outRealLowerBand = new double[allocationSize];
        int retCode = taLib.TA_ACCBANDS(startIdx, endIdx, high, low, close, optInTimePeriod, outBegIdx, outNBElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outRealUpperBand = outRealUpperBand;
        result.outRealMiddleBand = outRealMiddleBand;
        result.outRealLowerBand = outRealLowerBand;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}