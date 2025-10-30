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
 * The FastResult class is a result class that contains the Fast %K and Fast %D values.
 */
public class FastResult extends Result {
    private final double[] outFastK;
    private final double[] outFastD;

    /**
     * Constructs a new FastResult object.
     *
     * @param outFastK the Fast %K values.
     * @param outFastD the Fast %D values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public FastResult(double[] outFastK, double[] outFastD, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outFastK = outFastK;
        this.outFastD = outFastD;
    }

    /**
     * Returns the Fast %K values.
     *
     * @return the Fast %K values.
     */
    public double[] outFastK() {
        return outFastK;
    }

    /**
     * Returns the Fast %D values.
     *
     * @return the Fast %D values.
     */
    public double[] outFastD() {
        return outFastD;
    }

    /**
     * Returns a new FastResultBuilder object.
     *
     * @return a new FastResultBuilder object.
     */
    public static FastResultBuilder builder() {
        return new FastResultBuilder();
    }

    /**
     * The FastResultBuilder class is a builder for the FastResult class.
     */
    public static class FastResultBuilder extends Builder {
        private double[] outFastK;
        private double[] outFastD;

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
         * Sets the Fast %K values.
         *
         * @param outFastK the Fast %K values.
         * @return the builder.
         */
        public FastResultBuilder outFastK(double[] outFastK) {
            this.outFastK = outFastK;
            return this;
        }

        /**
         * Sets the Fast %D values.
         *
         * @param outFastD the Fast %D values.
         * @return the builder.
         */
        public FastResultBuilder outFastD(double[] outFastD) {
            this.outFastD = outFastD;
            return this;
        }

        /**
         * Builds a new FastResult object.
         *
         * @return a new FastResult object.
         */
        @Override
        public Result build() {
            return new FastResult(outFastK, outFastD, outBegIdx, outNBElement);
        }
    }
}
