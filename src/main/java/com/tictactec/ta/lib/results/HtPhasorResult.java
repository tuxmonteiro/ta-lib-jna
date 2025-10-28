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

public class HtPhasorResult extends Result {
    private final double[] outInPhase;
    private final double[] outQuadrature;

    public HtPhasorResult(double[] outInPhase, double[] outQuadrature, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outInPhase = outInPhase;
        this.outQuadrature = outQuadrature;
    }

    public double[] outInPhase() {
        return outInPhase;
    }

    public double[] outQuadrature() {
        return outQuadrature;
    }

    public static Builder builder() {
        return new HtPhasorResultBuilder();
    }

    public static class HtPhasorResultBuilder extends Builder {
        private double[] outInPhase;
        private double[] outQuadrature;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public Builder setOutInPhase(double[] outInPhase) {
            this.outInPhase = outInPhase;
            return this;
        }

        public Builder setOutQuadrature(double[] outQuadrature) {
            this.outQuadrature = outQuadrature;
            return this;
        }

        @Override
        public Result build() {
            return new HtPhasorResult(outInPhase, outQuadrature, outBegIdx, outNBElement);
        }
    }
}
