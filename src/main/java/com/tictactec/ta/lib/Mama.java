package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;

/**
 * This class is a wrapper for the TA-Lib function MAMA: MESA Adaptive Moving Average.
 */
public class Mama {

    private static final Logger logger = LoggerFactory.getLogger(Mama.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static Result execute(int startIdx, int endIdx, double[] inreal, double optInFastLimit, double optInSlowLimit) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal == null || inreal.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal' is null or too small for endIdx=" + endIdx);
        }

        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outMAMA = new double[allocationSize];
        double[] outFAMA = new double[allocationSize];
        int retCode = taLib.TA_MAMA(startIdx, endIdx, inreal, optInFastLimit, optInSlowLimit, outBegIdx, outNBElement, outMAMA, outFAMA);
        if (retCode != 0) {
            logger.error("TA-Lib function MAMA returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MAMA returned error code: " + retCode);
        }
        Result result = MamaResult.builder()
            .outMAMA(outMAMA)
            .outFAMA(outFAMA)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}