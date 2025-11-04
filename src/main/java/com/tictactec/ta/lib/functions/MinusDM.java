package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function MINUS_DM: Minus Directional Movement.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class MinusDM {

    private static final Logger logger = LoggerFactory.getLogger(MinusDM.class);
    private static final TALib taLib = TALib.INSTANCE;

    /**
     * Calculates the Minus Directional Movement of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param high the input series of high prices
     * @param low the input series of low prices
     * @param optInTimePeriod the time period for the calculation
     * @return a Result object containing the calculated Minus Directional Movement
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, int optInTimePeriod) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (high == null || high.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'high' is null or too small for endIdx=" + endIdx);
        }
        if (low == null || low.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'low' is null or too small for endIdx=" + endIdx);
        }

        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_MINUS_DM(startIdx, endIdx, high, low, optInTimePeriod, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function MINUS_DM returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MINUS_DM returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
