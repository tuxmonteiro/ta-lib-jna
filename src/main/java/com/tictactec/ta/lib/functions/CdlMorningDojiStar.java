package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function CDLMORNINGDOJISTAR: Morning Doji Star.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class CdlMorningDojiStar {

    private static final Logger logger = LoggerFactory.getLogger(CdlMorningDojiStar.class);
    private static final TALib taLib = TALib.INSTANCE;

    private CdlMorningDojiStar() {
    }

    /**
     * Calculates the Morning Doji Star pattern of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param open the input series of open prices
     * @param high the input series of high prices
     * @param low the input series of low prices
     * @param close the input series of close prices
     * @param optInPenetration the penetration factor
     * @return a Result object containing the calculated Morning Doji Star pattern
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] open, double[] high, double[] low, double[] close, double optInPenetration) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (open == null || open.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'open' is null or too small for endIdx=" + endIdx);
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
        int allocationSize = open.length;
        int[] outInteger = new int[allocationSize];
        int retCode = taLib.TA_CDLMORNINGDOJISTAR(startIdx, endIdx, open, high, low, close, optInPenetration, outBegIdx, outNBElement, outInteger);
        if (retCode != 0) {
            logger.error("TA-Lib function CDLMORNINGDOJISTAR returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function CDLMORNINGDOJISTAR returned error code: " + retCode);
        }
        Result result = IntegerResult.builder()
            .outInteger(outInteger)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
