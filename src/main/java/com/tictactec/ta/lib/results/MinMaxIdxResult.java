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
    private final double[] outMinIdx;
    private final double[] outMaxIdx;

    public MinMaxIdxResult(double[] outMinIdx, double[] outMaxIdx, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMinIdx = outMinIdx;
        this.outMaxIdx = outMaxIdx;
    }

    public double[] outMinIdx() {
        return outMinIdx;
    }

    public double[] outMaxIdx() {
        return outMaxIdx;
    }

    public static Builder builder() {
        return new MinMaxIdxResultBuilder();
    }

    public static class MinMaxIdxResultBuilder extends Builder {
        private double[] outMinIdx;
        private double[] outMaxIdx;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public MinMaxIdxResultBuilder setOutMinIdx(double[] outMinIdx) {
            this.outMinIdx = outMinIdx;
            return this;
        }

        public MinMaxIdxResultBuilder setOutMaxIdx(double[] outMaxIdx) {
            this.outMaxIdx = outMaxIdx;
            return this;
        }

        @Override
        public Result build() {
            return new MinMaxIdxResult(outMinIdx, outMaxIdx, outBegIdx, outNBElement);
        }
    }
}
