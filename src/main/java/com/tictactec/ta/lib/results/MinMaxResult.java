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

public class MinMaxResult extends Result {
    private final double[] outMin;
    private final double[] outMax;

    public MinMaxResult(double[] outMin, double[] outMax, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMin = outMin;
        this.outMax = outMax;
    }

    public double[] outMin() {
        return outMin;
    }

    public double[] outMax() {
        return outMax;
    }

    public static Builder builder() {
        return new MinMaxResultBuilder();
    }

    public static class MinMaxResultBuilder extends Builder {
        private double[] outMin;
        private double[] outMax;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public MinMaxResultBuilder setOutMin(double[] outMin) {
            this.outMin = outMin;
            return this;
        }

        public MinMaxResultBuilder setOutMax(double[] outMax) {
            this.outMax = outMax;
            return this;
        }

        @Override
        public Result build() {
            return new MinMaxResult(outMin, outMax, outBegIdx, outNBElement);
        }
    }
}
