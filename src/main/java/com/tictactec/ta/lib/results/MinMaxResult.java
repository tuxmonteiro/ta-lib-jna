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
 * The MinMaxResult class is a result class that contains the minimum and maximum values.
 */
public class MinMaxResult extends Result {
    private final double[] outMin;
    private final double[] outMax;

    /**
     * Constructs a new MinMaxResult object.
     *
     * @param outMin the minimum values.
     * @param outMax the maximum values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public MinMaxResult(double[] outMin, double[] outMax, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMin = outMin;
        this.outMax = outMax;
    }

    /**
     * Returns the minimum values.
     *
     * @return the minimum values.
     */
    public double[] outMin() {
        return outMin;
    }

    /**
     * Returns the maximum values.
     *
     * @return the maximum values.
     */
    public double[] outMax() {
        return outMax;
    }

    /**
     * Returns a new MinMaxResultBuilder object.
     *
     * @return a new MinMaxResultBuilder object.
     */
    public static MinMaxResultBuilder builder() {
        return new MinMaxResultBuilder();
    }

    /**
     * The MinMaxResultBuilder class is a builder for the MinMaxResult class.
     */
    public static class MinMaxResultBuilder extends Builder {
        private double[] outMin;
        private double[] outMax;

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
         * Sets the minimum values.
         *
         * @param outMin the minimum values.
         * @return the builder.
         */
        public MinMaxResultBuilder outMin(double[] outMin) {
            this.outMin = outMin;
            return this;
        }

        /**
         * Sets the maximum values.
         *
         * @param outMax the maximum values.
         * @return the builder.
         */
        public MinMaxResultBuilder outMax(double[] outMax) {
            this.outMax = outMax;
            return this;
        }

        /**
         * Builds a new MinMaxResult object.
         *
         * @return a new MinMaxResult object.
         */
        @Override
        public Result build() {
            return new MinMaxResult(outMin, outMax, outBegIdx, outNBElement);
        }
    }
}
