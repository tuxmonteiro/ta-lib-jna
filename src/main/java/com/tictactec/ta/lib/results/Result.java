package com.tictactec.ta.lib.results;

public abstract class Result {
    private final int outBegIdx;
    private final int outNBElement;

    public Result(int outBegIdx, int outNBElement) {
        this.outBegIdx = outBegIdx;
        this.outNBElement = outNBElement;
    }

    public int outNBElement() {
        return outNBElement;
    }

    public int outBegIdx() {
        return outBegIdx;
    }

    public static abstract class Builder {
        protected int outBegIdx;
        protected int outNBElement;

        protected Builder setOutNBElement(int outNBElement) {
            this.outNBElement = outNBElement;
            return this;
        }

        protected Builder setOutBegIdx(int outBegIdx) {
            this.outBegIdx = outBegIdx;
            return this;
        }

        protected abstract Result build();
    }
}
