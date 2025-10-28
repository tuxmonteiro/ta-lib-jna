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

public abstract class Result {
    private final int outBegIdx;
    private final int outNBElement;

    public Result(int outBegIdx, int outNBElement) {
        this.outBegIdx = outBegIdx;
        this.outNBElement = outNBElement;
    }

    public int outNBElement() {
        return outNBElement;
    }

    public int outBegIdx() {
        return outBegIdx;
    }

    public static abstract class Builder {
        protected int outBegIdx;
        protected int outNBElement;

        public Builder outBegIdx(int outBegIdx) {
            this.outBegIdx = outBegIdx;
            return this;
        }

        public Builder outNBElement(int outNBElement) {
            this.outNBElement = outNBElement;
            return this;
        }

        public abstract Result build();
    }
}
