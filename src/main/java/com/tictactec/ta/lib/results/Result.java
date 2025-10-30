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
 * The Result class is an abstract class that serves as a base class for all function result classes.
 * It contains the output beginning index and the number of elements in the result.
 */
public abstract class Result {
    private final int outBegIdx;
    private final int outNBElement;

    /**
     * Constructs a new Result object.
     *
     * @param outBegIdx the beginning index of the result.
     * @param outNBElement the number of elements in the result.
     */
    public Result(int outBegIdx, int outNBElement) {
        this.outBegIdx = outBegIdx;
        this.outNBElement = outNBElement;
    }

    /**
     * Returns the number of elements in the result.
     *
     * @return the number of elements in the result.
     */
    public int outNBElement() {
        return outNBElement;
    }

    /**
     * Returns the beginning index of the result.
     *
     * @return the beginning index of the result.
     */
    public int outBegIdx() {
        return outBegIdx;
    }

    /**
     * The Builder class is an abstract builder for the Result class.
     */
    public static abstract class Builder {
        protected int outBegIdx;
        protected int outNBElement;

        /**
         * Sets the beginning index of the result.
         *
         * @param outBegIdx the beginning index of the result.
         * @return the builder.
         */
        public Builder outBegIdx(int outBegIdx) {
            this.outBegIdx = outBegIdx;
            return this;
        }

        /**
         * Sets the number of elements in the result.
         *
         * @param outNBElement the number of elements in the result.
         * @return the builder.
         */
        public Builder outNBElement(int outNBElement) {
            this.outNBElement = outNBElement;
            return this;
        }

        /**
         * Builds a new Result object.
         *
         * @return a new Result object.
         */
        public abstract Result build();
    }
}
