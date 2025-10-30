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
 * The SlowResult class is a result class that contains the Slow %K and Slow %D values.
 */
public class SlowResult extends Result {
    private final double[] outSlowK;
    private final double[] outSlowD;

    /**
     * Constructs a new SlowResult object.
     *
     * @param outSlowK the Slow %K values.
     * @param outSlowD the Slow %D values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public SlowResult(double[] outSlowK, double[] outSlowD, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outSlowK = outSlowK;
        this.outSlowD = outSlowD;
    }

    /**
     * Returns the Slow %K values.
     *
     * @return the Slow %K values.
     */
    public double[] outSlowK() {
        return outSlowK;
    }

    /**
     * Returns the Slow %D values.
     *
     * @return the Slow %D values.
     */
    public double[] outSlowD() {
        return outSlowD;
    }

    /**
     * Returns a new SlowResultBuilder object.
     *
     * @return a new SlowResultBuilder object.
     */
    public static SlowResultBuilder builder() {
        return new SlowResultBuilder();
    }

    /**
     * The SlowResultBuilder class is a builder for the SlowResult class.
     */
    public static class SlowResultBuilder extends Builder {
        private double[] outSlowK;
        private double[] outSlowD;

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
         * Sets the Slow %K values.
         *
         * @param outSlowK the Slow %K values.
         * @return the builder.
         */
        public SlowResultBuilder outSlowK(double[] outSlowK) {
            this.outSlowK = outSlowK;
            return this;
        }

        /**
         * Sets the Slow %D values.
         *
         * @param outSlowD the Slow %D values.
         * @return the builder.
         */
        public SlowResultBuilder outSlowD(double[] outSlowD) {
            this.outSlowD = outSlowD;
            return this;
        }

        /**
         * Builds a new SlowResult object.
         *
         * @return a new SlowResult object.
         */
        @Override
        public Result build() {
            return new SlowResult(outSlowK, outSlowD, outBegIdx, outNBElement);
        }
    }
}
