package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function ULTOSC: Ultimate Oscillator.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class UltOsc {

    private static final Logger logger = LoggerFactory.getLogger(UltOsc.class);
    private static final TALib taLib = TALib.INSTANCE;

    /**
     * Calculates the Ultimate Oscillator of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param high the input series of high prices
     * @param low the input series of low prices
     * @param close the input series of close prices
     * @param optInFirstPeriod the first time period for the calculation
     * @param optInSecondPeriod the second time period for the calculation
     * @param optInThirdPeriod the third time period for the calculation
     * @return a Result object containing the calculated Ultimate Oscillator
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, int optInFirstPeriod, int optInSecondPeriod, int optInThirdPeriod) throws ArithmeticException, IndexOutOfBoundsException {
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
        if (close == null || close.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'close' is null or too small for endIdx=" + endIdx);
        }

        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = high.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_ULTOSC(startIdx, endIdx, high, low, close, optInFirstPeriod, optInSecondPeriod, optInThirdPeriod, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function ULTOSC returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function ULTOSC returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
