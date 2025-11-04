package com.tictactec.ta.lib.functions;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tictactec.ta.lib.results.*;
import com.tictactec.ta.lib.TALib;


/**
 * This class is a wrapper for the TA-Lib function PPO: Percentage Price Oscillator.
 *
 * @author fibonsai
 * @since 0.6.4
 */
public class Ppo {

    private static final Logger logger = LoggerFactory.getLogger(Ppo.class);
    private static final TALib taLib = TALib.INSTANCE;

    /**
     * Calculates the Percentage Price Oscillator of a given input series.
     *
     * @param startIdx the start index for the calculation
     * @param endIdx the end index for the calculation
     * @param inreal the input series
     * @param optInFastPeriod the time period for the fast moving average
     * @param optInSlowPeriod the time period for the slow moving average
     * @param optInMAType the moving average type
     * @return a Result object containing the calculated PPO
     * @throws ArithmeticException if the TA-Lib function returns an error code
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     */
    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInFastPeriod, int optInSlowPeriod, int optInMAType) throws ArithmeticException, IndexOutOfBoundsException {
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
        int retCode = taLib.TA_PPO(startIdx, endIdx, inreal, optInFastPeriod, optInSlowPeriod, optInMAType, outBegIdx, outNBElement, outReal);
        if (retCode != 0) {
            logger.error("TA-Lib function PPO returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function PPO returned error code: " + retCode);
        }
        Result result = RealResult.builder()
            .outReal(outReal)
            .outBegIdx(outBegIdx.getValue())
            .outNBElement(outNBElement.getValue())
            .build();
        return result;
    }
}
