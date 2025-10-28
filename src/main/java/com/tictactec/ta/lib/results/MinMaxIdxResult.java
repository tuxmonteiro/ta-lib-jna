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

public class MinMaxIdxResult extends Result {
    private final int[] outMinIdx;
    private final int[] outMaxIdx;

    public MinMaxIdxResult(int[] outMinIdx, int[] outMaxIdx, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMinIdx = outMinIdx;
        this.outMaxIdx = outMaxIdx;
    }

    public int[] outMinIdx() {
        return outMinIdx;
    }

    public int[] outMaxIdx() {
        return outMaxIdx;
    }

    public static MinMaxIdxResultBuilder builder() {
        return new MinMaxIdxResultBuilder();
    }

    public static class MinMaxIdxResultBuilder extends Builder {
        private int[] outMinIdx;
        private int[] outMaxIdx;

        @Override
        public Builder outNBElement(int outBegIdx) {
            return super.outNBElement(outBegIdx);
        }

        @Override
        public Builder outBegIdx(int outNBElement) {
            return super.outBegIdx(outNBElement);
        }

        public MinMaxIdxResultBuilder outMinIdx(int[] outMinIdx) {
            this.outMinIdx = outMinIdx;
            return this;
        }

        public MinMaxIdxResultBuilder outMaxIdx(int[] outMaxIdx) {
            this.outMaxIdx = outMaxIdx;
            return this;
        }

        @Override
        public Result build() {
            return new MinMaxIdxResult(outMinIdx, outMaxIdx, outBegIdx, outNBElement);
        }
    }
}
