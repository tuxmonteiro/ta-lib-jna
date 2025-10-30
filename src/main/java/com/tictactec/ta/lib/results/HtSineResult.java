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
 * The HtSineResult class is a result class that contains the Sine and Lead Sine values.
 */
public class HtSineResult extends Result {
    private final double[] outSine;
    private final double[] outLeadSine;

    /**
     * Constructs a new HtSineResult object.
     *
     * @param outSine the Sine values.
     * @param outLeadSine the Lead Sine values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public HtSineResult(double[] outSine, double[] outLeadSine, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outSine = outSine;
        this.outLeadSine = outLeadSine;
    }

    /**
     * Returns the Sine values.
     *
     * @return the Sine values.
     */
    public double[] outSine() {
        return outSine;
    }

    /**
     * Returns the Lead Sine values.
     *
     * @return the Lead Sine values.
     */
    public double[] outLeadSine() {
        return outLeadSine;
    }

    /**
     * Returns a new HtSineResultBuilder object.
     *
     * @return a new HtSineResultBuilder object.
     */
    public static HtSineResultBuilder builder() {
        return new HtSineResultBuilder();
    }

    /**
     * The HtSineResultBuilder class is a builder for the HtSineResult class.
     */
    public static class HtSineResultBuilder extends Builder {
        private double[] outSine;
        private double[] outLeadSine;

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
         * Sets the Sine values.
         *
         * @param outSine the Sine values.
         * @return the builder.
         */
        public HtSineResultBuilder outSine(double[] outSine) {
            this.outSine = outSine;
            return this;
        }

        /**
         * Sets the Lead Sine values.
         *
         * @param outLeadSine the Lead Sine values.
         * @return the builder.
         */
        public HtSineResultBuilder outLeadSine(double[] outLeadSine) {
            this.outLeadSine = outLeadSine;
            return this;
        }

        /**
         * Builds a new HtSineResult object.
         *
         * @return a new HtSineResult object.
         */
        @Override
        public Result build() {
            return new HtSineResult(outSine, outLeadSine, outBegIdx, outNBElement);
        }
    }
}
