package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;

/**
 * This class is a wrapper for the TA-Lib function MAVP: Moving average with variable period.
 */
public class MovingAverageVariablePeriod {

    private static final Logger logger = LoggerFactory.getLogger(MovingAverageVariablePeriod.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static Result execute(int startIdx, int endIdx, double[] inreal, double[] inperiods, int optInMinimumPeriod, int optInMaximumPeriod, int optInMAType) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal == null || inreal.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal' is null or too small for endIdx=" + endIdx);
        }
        if (inperiods == null || inperiods.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inperiods' is null or too small for endIdx=" + endIdx);
        }

        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outReal = new double[allocationSize];
        int retCode = taLib.TA_MAVP(startIdx, endIdx, inreal, inperiods, optInMinimumPeriod, optInMaximumPeriod, optInMAType, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function MAVP returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MAVP returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}