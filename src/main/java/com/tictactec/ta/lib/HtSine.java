package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function HT_SINE: Hilbert Transform - SineWave.
 */
public class HtSine {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outSine;
        public double[] outLeadSine;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outSine = new double[allocationSize];
        double[] outLeadSine = new double[allocationSize];
        int retCode = taLib.TA_HT_SINE(startIdx, endIdx, inreal, outBegIdx, outNBElement, outSine, outLeadSine);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outSine = outSine;
        result.outLeadSine = outLeadSine;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}