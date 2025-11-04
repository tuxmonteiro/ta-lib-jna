package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function SAREXT: Parabolic SAR - Extended.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class SarExt {

    private static final Logger logger = LoggerFactory.getLogger(SarExt.class);
    private static final TALib taLib = TALib.INSTANCE;

    /**
     * Calculates the Parabolic SAR - Extended of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param high the input series of high prices
     * @param low the input series of low prices
     * @param optInStartValue the start value for the calculation
     * @param optInOffsetonReverse the offset on reverse
     * @param optInAFInitLong the initial acceleration factor for long trades
     * @param optInAFLong the acceleration factor for long trades
     * @param optInAFMaxLong the maximum acceleration factor for long trades
     * @param optInAFInitShort the initial acceleration factor for short trades
     * @param optInAFShort the acceleration factor for short trades
     * @param optInAFMaxShort the maximum acceleration factor for short trades
     * @return a Result object containing the calculated Parabolic SAR - Extended
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double optInStartValue, double optInOffsetonReverse, double optInAFInitLong, double optInAFLong, double optInAFMaxLong, double optInAFInitShort, double optInAFShort, double optInAFMaxShort) throws ArithmeticException, IndexOutOfBoundsException {
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
        int retCode = taLib.TA_SAREXT(startIdx, endIdx, high, low, optInStartValue, optInOffsetonReverse, optInAFInitLong, optInAFLong, optInAFMaxLong, optInAFInitShort, optInAFShort, optInAFMaxShort, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function SAREXT returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function SAREXT returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
