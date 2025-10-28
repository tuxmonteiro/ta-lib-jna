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

public class FastResult extends Result {
    private final double[] outFastK;
    private final double[] outFastD;

    public FastResult(double[] outFastK, double[] outFastD, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outFastK = outFastK;
        this.outFastD = outFastD;
    }

    public double[] outFastK() {
        return outFastK;
    }

    public double[] outFastD() {
        return outFastD;
    }

    public static Builder builder() {
        return new FastResultBuilder();
    }

    public static class FastResultBuilder extends Builder {
        private double[] outFastK;
        private double[] outFastD;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public FastResultBuilder setOutFastK(double[] outFastK) {
            this.outFastK = outFastK;
            return this;
        }

        public FastResultBuilder setOutFastD(double[] outFastD) {
            this.outFastD = outFastD;
            return this;
        }

        @Override
        public Result build() {
            return new FastResult(outFastK, outFastD, outBegIdx, outNBElement);
        }
    }
}
