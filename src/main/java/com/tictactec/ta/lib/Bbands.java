package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;

/**
 * This class is a wrapper for the TA-Lib function BBANDS: Bollinger Bands.
 */
public class Bbands {

    private static final Logger logger = LoggerFactory.getLogger(Bbands.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInTimePeriod, double optInDeviationsup, double optInDeviationsdown, int optInMAType) throws ArithmeticException, IndexOutOfBoundsException {
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
        double[] outRealUpperBand = new double[allocationSize];
        double[] outRealMiddleBand = new double[allocationSize];
        double[] outRealLowerBand = new double[allocationSize];
        int retCode = taLib.TA_BBANDS(startIdx, endIdx, inreal, optInTimePeriod, optInDeviationsup, optInDeviationsdown, optInMAType, outBegIdx, outNBElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);
        if (retCode != 0) {
            logger.error("TA-Lib function BBANDS returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function BBANDS returned error code: " + retCode);
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