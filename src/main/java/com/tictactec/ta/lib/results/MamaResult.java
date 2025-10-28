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

public class MamaResult extends Result {
    private final double[] outMAMA;
    private final double[] outFAMA;

    public MamaResult(double[] outMAMA, double[] outFAMA, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMAMA = outMAMA;
        this.outFAMA = outFAMA;
    }

    public double[] outMAMA() {
        return outMAMA;
    }

    public double[] outFAMA() {
        return outFAMA;
    }

    public static MamaResultBuilder builder() {
        return new MamaResultBuilder();
    }

    public static class MamaResultBuilder extends Builder {
        private double[] outMAMA;
        private double[] outFAMA;

        @Override
        public Builder outNBElement(int outBegIdx) {
            return super.outNBElement(outBegIdx);
        }

        @Override
        public Builder outBegIdx(int outNBElement) {
            return super.outBegIdx(outNBElement);
        }

        public MamaResultBuilder outMAMA(double[] outMAMA) {
            this.outMAMA = outMAMA;
            return this;
        }

        public MamaResultBuilder outFAMA(double[] outFAMA) {
            this.outFAMA = outFAMA;
            return this;
        }

        @Override
        public Result build() {
            return new MamaResult(outMAMA, outFAMA, outBegIdx, outNBElement);
        }
    }
}
