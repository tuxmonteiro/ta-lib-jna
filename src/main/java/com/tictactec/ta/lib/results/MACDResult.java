/*
 *  Copyright (c) 2025 fibonsai.com
 *  All rights reserved.
 *
 *  This source is subject to the Apache License, Version 2.0.
 *  Please see the LICENSE file for more information.
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.tictactec.ta.lib.results;


/**
 * The MACDResult class is a result class that contains the MACD, MACD Signal, and MACD Histogram values.
 */
public class MACDResult extends Result {
    private final double[] outMACD;
    private final double[] outMACDSignal;
    private final double[] outMACDHist;

    /**
     * Constructs a new MACDResult object.
     *
     * @param outMACD the MACD values.
     * @param outMACDSignal the MACD Signal values.
     * @param outMACDHist the MACD Histogram values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public MACDResult(double[] outMACD, double[] outMACDSignal, double[] outMACDHist, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMACD = outMACD;
        this.outMACDSignal = outMACDSignal;
        this.outMACDHist = outMACDHist;
    }

    /**
     * Returns the MACD values.
     *
     * @return the MACD values.
     */
    public double[] outMACD() {
        return outMACD;
    }

    /**
     * Returns the MACD Signal values.
     *
     * @return the MACD Signal values.
     */
    public double[] outMACDSignal() {
        return outMACDSignal;
    }

    /**
     * Returns the MACD Histogram values.
     *
     * @return the MACD Histogram values.
     */
    public double[] outMACDHist() {
        return outMACDHist;
    }

    /**
     * Returns a new MACDResultBuilder object.
     *
     * @return a new MACDResultBuilder object.
     */
    public static MACDResultBuilder builder() {
        return new MACDResultBuilder();
    }

    /**
     * The MACDResultBuilder class is a builder for the MACDResult class.
     */
    public static class MACDResultBuilder extends Builder {
        private double[] outMACD;
        private double[] outMACDSignal;
        private double[] outMACDHist;

        /**
         * Sets the number of elements in the result.
         *
         * @param outNBElement the number of elements in the result.
         * @return the builder.
         */
        @Override
        public Builder outNBElement(int outNBElement) {
            return super.outNBElement(outNBElement);
        }

        /**
         * Sets the beginning index of the result.
         *
         * @param outBegIdx the beginning index of the result.
         * @return the builder.
         */
        @Override
        public Builder outBegIdx(int outBegIdx) {
            return super.outBegIdx(outBegIdx);
        }

        /**
         * Sets the MACD values.
         *
         * @param outMACD the MACD values.
         * @return the builder.
         */
        public MACDResultBuilder outMACD(double[] outMACD) {
            this.outMACD = outMACD;
            return this;
        }

        /**
         * Sets the MACD Signal values.
         *
         * @param outMACDSignal the MACD Signal values.
         * @return the builder.
         */
        public MACDResultBuilder outMACDSignal(double[] outMACDSignal) {
            this.outMACDSignal = outMACDSignal;
            return this;
        }

        /**
         * Sets the MACD Histogram values.
         *
         * @param outMACDHist the MACD Histogram values.
         * @return the builder.
         */
        public MACDResultBuilder outMACDHist(double[] outMACDHist) {
            this.outMACDHist = outMACDHist;
            return this;
        }

        /**
         * Builds a new MACDResult object.
         *
         * @return a new MACDResult object.
         */
        @Override
        public Result build() {
            return new MACDResult(outMACD, outMACDSignal, outMACDHist, outBegIdx, outNBElement);
        }
    }
}
