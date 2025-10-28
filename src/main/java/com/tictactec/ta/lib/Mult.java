package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function MULT: Vector Arithmetic Mult.
 */
public class Mult {

    private static final Logger logger = LoggerFactory.getLogger(Mult.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outReal;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal0, double[] inreal1) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal0 == null || inreal0.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal0' is null or too small for endIdx=" + endIdx);
        }
        if (inreal1 == null || inreal1.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal1' is null or too small for endIdx=" + endIdx);
        }

        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal0.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_MULT(startIdx, endIdx, inreal0, inreal1, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function MULT returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MULT returned error code: " + retCode);
        }
        result.outReal = outReal;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}