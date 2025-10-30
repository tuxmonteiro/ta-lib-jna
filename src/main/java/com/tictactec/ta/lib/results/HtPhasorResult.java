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
 * The HtPhasorResult class is a result class that contains the In-Phase and Quadrature components.
 */
public class HtPhasorResult extends Result {
    private final double[] outInPhase;
    private final double[] outQuadrature;

    /**
     * Constructs a new HtPhasorResult object.
     *
     * @param outInPhase the In-Phase components.
     * @param outQuadrature the Quadrature components.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public HtPhasorResult(double[] outInPhase, double[] outQuadrature, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outInPhase = outInPhase;
        this.outQuadrature = outQuadrature;
    }

    /**
     * Returns the In-Phase components.
     *
     * @return the In-Phase components.
     */
    public double[] outInPhase() {
        return outInPhase;
    }

    /**
     * Returns the Quadrature components.
     *
     * @return the Quadrature components.
     */
    public double[] outQuadrature() {
        return outQuadrature;
    }

    /**
     * Returns a new HtPhasorResultBuilder object.
     *
     * @return a new HtPhasorResultBuilder object.
     */
    public static HtPhasorResultBuilder builder() {
        return new HtPhasorResultBuilder();
    }

    /**
     * The HtPhasorResultBuilder class is a builder for the HtPhasorResult class.
     */
    public static class HtPhasorResultBuilder extends Builder {
        private double[] outInPhase;
        private double[] outQuadrature;

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
         * Sets the In-Phase components.
         *
         * @param outInPhase the In-Phase components.
         * @return the builder.
         */
        public HtPhasorResultBuilder outInPhase(double[] outInPhase) {
            this.outInPhase = outInPhase;
            return this;
        }

        /**
         * Sets the Quadrature components.
         *
         * @param outQuadrature the Quadrature components.
         * @return the builder.
         */
        public HtPhasorResultBuilder outQuadrature(double[] outQuadrature) {
            this.outQuadrature = outQuadrature;
            return this;
        }

        /**
         * Builds a new HtPhasorResult object.
         *
         * @return a new HtPhasorResult object.
         */
        @Override
        public Result build() {
            return new HtPhasorResult(outInPhase, outQuadrature, outBegIdx, outNBElement);
        }
    }
}
