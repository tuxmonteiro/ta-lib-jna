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
 * The MinMaxIdxResult class is a result class that contains the index of the minimum and maximum values.
 */
public class MinMaxIdxResult extends Result {
    private final int[] outMinIdx;
    private final int[] outMaxIdx;

    /**
     * Constructs a new MinMaxIdxResult object.
     *
     * @param outMinIdx the index of the minimum values.
     * @param outMaxIdx the index of the maximum values.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public MinMaxIdxResult(int[] outMinIdx, int[] outMaxIdx, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMinIdx = outMinIdx;
        this.outMaxIdx = outMaxIdx;
    }

    /**
     * Returns the index of the minimum values.
     *
     * @return the index of the minimum values.
     */
    public int[] outMinIdx() {
        return outMinIdx;
    }

    /**
     * Returns the index of the maximum values.
     *
     * @return the index of the maximum values.
     */
    public int[] outMaxIdx() {
        return outMaxIdx;
    }

    /**
     * Returns a new MinMaxIdxResultBuilder object.
     *
     * @return a new MinMaxIdxResultBuilder object.
     */
    public static MinMaxIdxResultBuilder builder() {
        return new MinMaxIdxResultBuilder();
    }

    /**
     * The MinMaxIdxResultBuilder class is a builder for the MinMaxIdxResult class.
     */
    public static class MinMaxIdxResultBuilder extends Builder {
        private int[] outMinIdx;
        private int[] outMaxIdx;

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
         * Sets the index of the minimum values.
         *
         * @param outMinIdx the index of the minimum values.
         * @return the builder.
         */
        public MinMaxIdxResultBuilder outMinIdx(int[] outMinIdx) {
            this.outMinIdx = outMinIdx;
            return this;
        }

        /**
         * Sets the index of the maximum values.
         *
         * @param outMaxIdx the index of the maximum values.
         * @return the builder.
         */
        public MinMaxIdxResultBuilder outMaxIdx(int[] outMaxIdx) {
            this.outMaxIdx = outMaxIdx;
            return this;
        }

        /**
         * Builds a new MinMaxIdxResult object.
         *
         * @return a new MinMaxIdxResult object.
         */
        @Override
        public Result build() {
            return new MinMaxIdxResult(outMinIdx, outMaxIdx, outBegIdx, outNBElement);
        }
    }
}
