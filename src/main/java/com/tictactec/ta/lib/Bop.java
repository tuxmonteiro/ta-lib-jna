package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function BOP: Balance Of Power.
 */
public class Bop {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outReal;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] open, double[] high, double[] low, double[] close) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = open.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_BOP(startIdx, endIdx, open, high, low, close, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outReal = outReal;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}