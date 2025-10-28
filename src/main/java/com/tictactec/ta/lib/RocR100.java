package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function ROCR100: Rate of change ratio 100 scale: (price/prevPrice)*100.
 */
public class RocR100 {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outReal;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_ROCR100(startIdx, endIdx, inreal, optInTimePeriod, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outReal = outReal;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}