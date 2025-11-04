package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function SQRT: Vector Square Root.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class Sqrt {

    private static final Logger logger = LoggerFactory.getLogger(Sqrt.class);
    private static final TALib taLib = TALib.INSTANCE;

    /**
     * Calculates the vector square root of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param inreal the input series
     * @return a Result object containing the calculated vector square root
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] inreal) throws ArithmeticException, IndexOutOfBoundsException {
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
        int retCode = taLib.TA_SQRT(startIdx, endIdx, inreal, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function SQRT returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function SQRT returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
