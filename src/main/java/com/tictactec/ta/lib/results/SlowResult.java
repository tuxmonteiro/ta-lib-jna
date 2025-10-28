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

public class SlowResult extends Result {
    private final double[] outSlowK;
    private final double[] outSlowD;

    public SlowResult(double[] outSlowK, double[] outSlowD, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outSlowK = outSlowK;
        this.outSlowD = outSlowD;
    }

    public double[] outSlowK() {
        return outSlowK;
    }

    public double[] outSlowD() {
        return outSlowD;
    }

    public static Builder builder() {
        return new SlowResultBuilder();
    }

    public static class SlowResultBuilder extends Builder {
        private double[] outSlowK;
        private double[] outSlowD;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public SlowResultBuilder setOutSlowK(double[] outSlowK) {
            this.outSlowK = outSlowK;
            return this;
        }

        public SlowResultBuilder setOutSlowD(double[] outSlowD) {
            this.outSlowD = outSlowD;
            return this;
        }

        @Override
        public Result build() {
            return new SlowResult(outSlowK, outSlowD, outBegIdx, outNBElement);
        }
    }
}
