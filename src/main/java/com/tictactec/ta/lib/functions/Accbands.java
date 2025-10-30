package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;

/**
 * This class is a wrapper for the TA-Lib function ACCBANDS: Acceleration Bands.
 */
public class Accbands {

    private static final Logger logger = LoggerFactory.getLogger(Accbands.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static Result execute(int startIdx, int endIdx, double[] high, double[] low, double[] close, int optInTimePeriod) throws ArithmeticException, IndexOutOfBoundsException {
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
        double[] outRealUpperBand = new double[allocationSize];
        double[] outRealMiddleBand = new double[allocationSize];
        double[] outRealLowerBand = new double[allocationSize];
        int retCode = taLib.TA_ACCBANDS(startIdx, endIdx, high, low, close, optInTimePeriod, outBegIdx, outNBElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);
        if (retCode != 0) {
            logger.error("TA-Lib function ACCBANDS returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function ACCBANDS returned error code: " + retCode);
        }
        Result result = BandsResult.builder()
            .outRealUpperBand(outRealUpperBand)
            .outRealMiddleBand(outRealMiddleBand)
            .outRealLowerBand(outRealLowerBand)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}