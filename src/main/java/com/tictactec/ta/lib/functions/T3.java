package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function T3: Triple Exponential Moving Average (T3).
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class T3 {

    private static final Logger logger = LoggerFactory.getLogger(T3.class);
    private static final TALib taLib = TALib.INSTANCE;

    /**
     * Calculates the Triple Exponential Moving Average (T3) of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param inreal the input series
     * @param optInTimePeriod the time period for the calculation
     * @param optInVolumeFactor the volume factor for the calculation
     * @return a Result object containing the calculated T3
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod, double optInVolumeFactor) throws ArithmeticException, IndexOutOfBoundsException {
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
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_T3(startIdx, endIdx, inreal, optInTimePeriod, optInVolumeFactor, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function T3 returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function T3 returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
