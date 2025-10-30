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
 * The RealResult class is a result class that contains an array of real numbers.
 */
public class RealResult extends Result {
    private final double[] outReal;

    /**
     * Constructs a new RealResult object.
     *
     * @param outReal the array of real numbers.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public RealResult(double[] outReal, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outReal = outReal;
    }

    /**
     * Returns the array of real numbers.
     *
     * @return the array of real numbers.
     */
    public double[] outReal() {
        return outReal;
    }

    /**
     * Returns a new RealResultBuilder object.
     *
     * @return a new RealResultBuilder object.
     */
    public static RealResultBuilder builder() {
        return new RealResultBuilder();
    }

    /**
     * The RealResultBuilder class is a builder for the RealResult class.
     */
    public static class RealResultBuilder extends Builder {
        private double[] outReal;

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
         * Sets the array of real numbers.
         *
         * @param outReal the array of real numbers.
         * @return the builder.
         */
        public RealResultBuilder outReal(double[] outReal) {
            this.outReal = outReal;
            return this;
        }

        /**
         * Builds a new RealResult object.
         *
         * @return a new RealResult object.
         */
        @Override
        public Result build() {
            return new RealResult(outReal, outBegIdx, outNBElement);
        }
    }
}
