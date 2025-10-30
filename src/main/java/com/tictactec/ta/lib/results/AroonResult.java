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
 * The AroonResult class is a result class that contains the Aroon Down and Aroon Up values.
 */
public class AroonResult extends Result {
    private final double[] outAroonDown;
    private final double[] outAroonUp;

    /**
     * Constructs a new AroonResult object.
     *
     * @param outAroonDown the Aroon Down values.
     * @param outAroonUp the Aroon Up values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public AroonResult(double[] outAroonDown, double[] outAroonUp, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outAroonDown = outAroonDown;
        this.outAroonUp = outAroonUp;
    }

    /**
     * Returns the Aroon Down values.
     *
     * @return the Aroon Down values.
     */
    public double[] outAroonDown() {
        return outAroonDown;
    }

    /**
     * Returns the Aroon Up values.
     *
     * @return the Aroon Up values.
     */
    public double[] outAroonUp() {
        return outAroonUp;
    }

    /**
     * Returns a new AroonResultBuilder object.
     *
     * @return a new AroonResultBuilder object.
     */
    public static AroonResultBuilder builder() {
        return new AroonResultBuilder();
    }

    /**
     * The AroonResultBuilder class is a builder for the AroonResult class.
     */
    public static class AroonResultBuilder extends Builder {
        private double[] outAroonDown;
        private double[] outAroonUp;

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
         * Sets the Aroon Down values.
         *
         * @param outAroonDown the Aroon Down values.
         * @return the builder.
         */
        public AroonResultBuilder outAroonDown(double[] outAroonDown) {
            this.outAroonDown = outAroonDown;
            return this;
        }

        /**
         * Sets the Aroon Up values.
         *
         * @param outAroonUp the Aroon Up values.
         * @return the builder.
         */
        public AroonResultBuilder outAroonUp(double[] outAroonUp) {
            this.outAroonUp = outAroonUp;
            return this;
        }

        /**
         * Builds a new AroonResult object.
         *
         * @return a new AroonResult object.
         */
        @Override
        public Result build() {
            return new AroonResult(outAroonDown, outAroonUp, outBegIdx, outNBElement);
        }
    }
}
