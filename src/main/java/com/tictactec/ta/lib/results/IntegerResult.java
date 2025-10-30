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
 * The IntegerResult class is a result class that contains an array of integers.
 */
public class IntegerResult extends Result {
    public final int[] outInteger;

    /**
     * Constructs a new IntegerResult object.
     *
     * @param outInteger the array of integers.
     * @param outNBElement the number of elements in the result.
     * @param outBegIdx the beginning index of the result.
     */
    public IntegerResult(int[] outInteger, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outInteger = outInteger;
    }

    /**
     * Returns the array of integers.
     *
     * @return the array of integers.
     */
    public int[] outInteger() {
        return outInteger;
    }

    /**
     * Returns a new IntegerResultBuilder object.
     *
     * @return a new IntegerResultBuilder object.
     */
    public static IntegerResultBuilder builder() {
        return new IntegerResultBuilder();
    }

    /**
     * The IntegerResultBuilder class is a builder for the IntegerResult class.
     */
    public static class IntegerResultBuilder extends Builder {
        public int[] outInteger;

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
         * Sets the array of integers.
         *
         * @param outInteger the array of integers.
         * @return the builder.
         */
        public IntegerResultBuilder outInteger(int[] outInteger) {
            this.outInteger = outInteger;
            return this;
        }

        /**
         * Builds a new IntegerResult object.
         *
         * @return a new IntegerResult object.
         */
        @Override
        public Result build() {
            return new IntegerResult(outInteger, outBegIdx, outNBElement);
        }
    }
}
