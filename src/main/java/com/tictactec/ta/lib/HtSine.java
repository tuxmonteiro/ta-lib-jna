package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function HT_SINE: Hilbert Transform - SineWave.
 */
public class HtSine {

    private static final Logger logger = LoggerFactory.getLogger(HtSine.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outSine;
        public double[] outLeadSine;
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
        double[] outSine = new double[allocationSize];
        double[] outLeadSine = new double[allocationSize];
        int retCode = taLib.TA_HT_SINE(startIdx, endIdx, inreal, outBegIdx, outNBElement, outSine, outLeadSine);
        if (retCode != 0) {
            logger.error("TA-Lib function HT_SINE returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function HT_SINE returned error code: " + retCode);
        }
        result.outSine = outSine;
        result.outLeadSine = outLeadSine;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}