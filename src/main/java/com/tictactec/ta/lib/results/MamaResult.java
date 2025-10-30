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
 * The MamaResult class is a result class that contains the MAMA and FAMA values.
 */
public class MamaResult extends Result {
    private final double[] outMAMA;
    private final double[] outFAMA;

    /**
     * Constructs a new MamaResult object.
     *
     * @param outMAMA the MAMA values.
     * @param outFAMA the FAMA values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public MamaResult(double[] outMAMA, double[] outFAMA, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMAMA = outMAMA;
        this.outFAMA = outFAMA;
    }

    /**
     * Returns the MAMA values.
     *
     * @return the MAMA values.
     */
    public double[] outMAMA() {
        return outMAMA;
    }

    /**
     * Returns the FAMA values.
     *
     * @return the FAMA values.
     */
    public double[] outFAMA() {
        return outFAMA;
    }

    /**
     * Returns a new MamaResultBuilder object.
     *
     * @return a new MamaResultBuilder object.
     */
    public static MamaResultBuilder builder() {
        return new MamaResultBuilder();
    }

    /**
     * The MamaResultBuilder class is a builder for the MamaResult class.
     */
    public static class MamaResultBuilder extends Builder {
        private double[] outMAMA;
        private double[] outFAMA;

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
         * Sets the MAMA values.
         *
         * @param outMAMA the MAMA values.
         * @return the builder.
         */
        public MamaResultBuilder outMAMA(double[] outMAMA) {
            this.outMAMA = outMAMA;
            return this;
        }

        /**
         * Sets the FAMA values.
         *
         * @param outFAMA the FAMA values.
         * @return the builder.
         */
        public MamaResultBuilder outFAMA(double[] outFAMA) {
            this.outFAMA = outFAMA;
            return this;
        }

        /**
         * Builds a new MamaResult object.
         *
         * @return a new MamaResult object.
         */
        @Override
        public Result build() {
            return new MamaResult(outMAMA, outFAMA, outBegIdx, outNBElement);
        }
    }
}
