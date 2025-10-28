package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function MACDEXT: MACD with controllable MA type.
 */
public class MacdExt {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outMACD;
        public double[] outMACDSignal;
        public double[] outMACDHist;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInFastPeriod, int optInFastMA, int optInSlowPeriod, int optInSlowMA, int optInSignalPeriod, int optInSignalMA) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outMACD = new double[allocationSize];
        double[] outMACDSignal = new double[allocationSize];
        double[] outMACDHist = new double[allocationSize];
        int retCode = taLib.TA_MACDEXT(startIdx, endIdx, inreal, optInFastPeriod, optInFastMA, optInSlowPeriod, optInSlowMA, optInSignalPeriod, optInSignalMA, outBegIdx, outNBElement, outMACD, outMACDSignal, outMACDHist);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outMACD = outMACD;
        result.outMACDSignal = outMACDSignal;
        result.outMACDHist = outMACDHist;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}