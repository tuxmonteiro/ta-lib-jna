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
 * The BandsResult class is a result class that contains the upper, middle, and lower bands.
 */
public class BandsResult extends Result {
    private final double[] outRealUpperBand;
    private final double[] outRealMiddleBand;
    private final double[] outRealLowerBand;

    /**
     * Constructs a new BandsResult object.
     *
     * @param outRealUpperBand the upper band values.
     * @param outRealMiddleBand the middle band values.
     * @param outRealLowerBand the lower band values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public BandsResult(double[] outRealUpperBand, double[] outRealMiddleBand, double[] outRealLowerBand, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outRealUpperBand = outRealUpperBand;
        this.outRealMiddleBand = outRealMiddleBand;
        this.outRealLowerBand = outRealLowerBand;
    }

    /**
     * Returns the upper band values.
     *
     * @return the upper band values.
     */
    public double[] outRealUpperBand() {
        return outRealUpperBand;
    }

    /**
     * Returns the middle band values.
     *
     * @return the middle band values.
     */
    public double[] outRealMiddleBand() {
        return outRealMiddleBand;
    }

    /**
     * Returns the lower band values.
     *
     * @return the lower band values.
     */
    public double[] outRealLowerBand() {
        return outRealLowerBand;
    }

    /**
     * Returns a new BandsResultBuilder object.
     *
     * @return a new BandsResultBuilder object.
     */
    public static BandsResultBuilder builder() {
        return new BandsResultBuilder();
    }

    /**
     * The BandsResultBuilder class is a builder for the BandsResult class.
     */
    public static class BandsResultBuilder extends Builder {
        private double[] outRealUpperBand;
        private double[] outRealMiddleBand;
        private double[] outRealLowerBand;

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
         * Sets the upper band values.
         *
         * @param outRealUpperBand the upper band values.
         * @return the builder.
         */
        public BandsResultBuilder outRealUpperBand(double[] outRealUpperBand) {
            this.outRealUpperBand = outRealUpperBand;
            return this;
        }

        /**
         * Sets the middle band values.
         *
         * @param outRealMiddleBand the middle band values.
         * @return the builder.
         */
        public BandsResultBuilder outRealMiddleBand(double[] outRealMiddleBand) {
            this.outRealMiddleBand = outRealMiddleBand;
            return this;
        }

        /**
         * Sets the lower band values.
         *
         * @param outRealLowerBand the lower band values.
         * @return the builder.
         */
        public BandsResultBuilder outRealLowerBand(double[] outRealLowerBand) {
            this.outRealLowerBand = outRealLowerBand;
            return this;
        }

        /**
         * Builds a new BandsResult object.
         *
         * @return a new BandsResult object.
         */
        @Override
        public Result build() {
            return new BandsResult(outRealUpperBand, outRealMiddleBand, outRealLowerBand, outBegIdx, outNBElement);
        }
    }
}
