package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function MAXINDEX: Index of highest value over a specified period.
 */
public class MaxIndex {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public int[] outInteger;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        int[] outInteger = new int[allocationSize];
        int retCode = taLib.TA_MAXINDEX(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outInteger);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outInteger = outInteger;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}