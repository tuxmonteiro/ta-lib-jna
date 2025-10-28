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

public class RealResult extends Result {
    private final double[] outReal;

    public RealResult(double[] outReal, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outReal = outReal;
    }

    public double[] outReal() {
        return outReal;
    }

    public static Builder builder() {
        return new RealResultBuilder();
    }

    public static class RealResultBuilder extends Builder {
        private double[] outReal;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public Builder setOutReal(double[] outReal) {
            this.outReal = outReal;
            return this;
        }

        @Override
        public Result build() {
            return new RealResult(outReal, outBegIdx, outNBElement);
        }
    }
}
