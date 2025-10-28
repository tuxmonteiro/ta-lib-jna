package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function HT_PHASOR: Hilbert Transform - Phasor Components.
 */
public class HtPhasor {

    private static final Logger logger = LoggerFactory.getLogger(HtPhasor.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outInPhase;
        public double[] outQuadrature;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal == null || inreal.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal' is null or too small for endIdx=" + endIdx);
        }

        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outInPhase = new double[allocationSize];
        double[] outQuadrature = new double[allocationSize];
        int retCode = taLib.TA_HT_PHASOR(startIdx, endIdx, inreal, outBegIdx, outNBElement, outInPhase, outQuadrature);
        if (retCode != 0) {
            logger.error("TA-Lib function HT_PHASOR returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function HT_PHASOR returned error code: " + retCode);
        }
        result.outInPhase = outInPhase;
        result.outQuadrature = outQuadrature;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}