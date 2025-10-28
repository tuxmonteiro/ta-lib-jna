package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function MINMAXINDEX: Indexes of lowest and highest values over a specified period.
 */
public class MinMaxIndex {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public int[] outMinIdx;
        public int[] outMaxIdx;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        int[] outMinIdx = new int[allocationSize];
        int[] outMaxIdx = new int[allocationSize];
        int retCode = taLib.TA_MINMAXINDEX(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outMinIdx, outMaxIdx);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outMinIdx = outMinIdx;
        result.outMaxIdx = outMaxIdx;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}