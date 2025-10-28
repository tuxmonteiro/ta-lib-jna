package com.tictactec.ta.lib;

import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a wrapper for the TA-Lib function MACDEXT: MACD with controllable MA type.
 */
public class MacdExt {

    private static final Logger logger = LoggerFactory.getLogger(MacdExt.class);
    private static final TALib taLib = TALib.INSTANCE;

    public static class Result {
        public double[] outMACD;
        public double[] outMACDSignal;
        public double[] outMACDHist;
        public int outBegIdx;
        public int outNBElement;
    }

    public static Result execute(int startIdx, int endIdx, double[] inreal, int optInFastPeriod, int optInFastMA, int optInSlowPeriod, int optInSlowMA, int optInSignalPeriod, int optInSignalMA) throws ArithmeticException, IndexOutOfBoundsException {
        // Input validation
        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {
            throw new IndexOutOfBoundsException("Invalid startIdx or endIdx. startIdx=" + startIdx + ", endIdx=" + endIdx);
        }
        if (inreal == null || inreal.length <= endIdx) {
            throw new IndexOutOfBoundsException("Input array 'inreal' is null or too small for endIdx=" + endIdx);
        }

        Result result = new Result();
        IntByReference outBegIdx = new IntByReference();
        IntByReference outNBElement = new IntByReference();
        int allocationSize = inreal.length;
        double[] outMACD = new double[allocationSize];
        double[] outMACDSignal = new double[allocationSize];
        double[] outMACDHist = new double[allocationSize];
        int retCode = taLib.TA_MACDEXT(startIdx, endIdx, inreal, optInFastPeriod, optInFastMA, optInSlowPeriod, optInSlowMA, optInSignalPeriod, optInSignalMA, outBegIdx, outNBElement, outMACD, outMACDSignal, outMACDHist);
        if (retCode != 0) {
            logger.error("TA-Lib function MACDEXT returned error code: {}", retCode);
            throw new ArithmeticException("TA-Lib function MACDEXT returned error code: " + retCode);
        }
        result.outMACD = outMACD;
        result.outMACDSignal = outMACDSignal;
        result.outMACDHist = outMACDHist;
        result.outBegIdx = outBegIdx.getValue();
        result.outNBElement = outNBElement.getValue();
        return result;
    }
}