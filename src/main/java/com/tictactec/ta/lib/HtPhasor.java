package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;

/**
 * This class is a wrapper for the TA-Lib function HT_PHASOR: Hilbert Transform - Phasor Components.
 */
public class HtPhasor {

    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outInPhase;
        public double[] outQuadrature;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal) {
        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outInPhase = new double[allocationSize];
        double[] outQuadrature = new double[allocationSize];
        int retCode = taLib.TA_HT_PHASOR(startIdx, endIdx, inreal, outBegIdx, outNBElement, outInPhase, outQuadrature);
        if (retCode != 0) {
            // Handle error code if necessary
        }
        result.outInPhase = outInPhase;
        result.outQuadrature = outQuadrature;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}