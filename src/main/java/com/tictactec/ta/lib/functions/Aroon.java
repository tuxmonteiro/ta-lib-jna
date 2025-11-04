package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;

/**
 * This class is a wrapper for the TA-Lib function AROON: Aroon.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class Aroon {

    private static final Logger logger = LoggerFactory.getLogger(Aroon.class);
    private static final TALib taLib = TALib.INSTANCE;

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
        double[] outAroonDown = new double[allocationSize];
        double[] outAroonUp = new double[allocationSize];
        int retCode = taLib.TA_AROON(startIdx, endIdx, high, low, optInTimePeriod, outBegIdx, outNBElement, outAroonDown, outAroonUp);
        if (retCode != 0) {
            logger.error("TA-Lib function AROON returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function AROON returned error code: " + retCode);
        }
        Result result = AroonResult.builder()
            .outAroonDown(outAroonDown)
            .outAroonUp(outAroonUp)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}