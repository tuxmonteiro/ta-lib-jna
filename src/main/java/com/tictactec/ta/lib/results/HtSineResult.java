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

public class HtSineResult extends Result {
    private final double[] outSine;
    private final double[] outLeadSine;

    public HtSineResult(double[] outSine, double[] outLeadSine, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outSine = outSine;
        this.outLeadSine = outLeadSine;
    }

    public double[] outSine() {
        return outSine;
    }

    public double[] outLeadSine() {
        return outLeadSine;
    }

    public static HtSineResultBuilder builder() {
        return new HtSineResultBuilder();
    }

    public static class HtSineResultBuilder extends Builder {
        private double[] outSine;
        private double[] outLeadSine;

        @Override
        public Builder outNBElement(int outBegIdx) {
            return super.outNBElement(outBegIdx);
        }

        @Override
        public Builder outBegIdx(int outNBElement) {
            return super.outBegIdx(outNBElement);
        }

        public HtSineResultBuilder outSine(double[] outSine) {
            this.outSine = outSine;
            return this;
        }

        public HtSineResultBuilder outLeadSine(double[] outLeadSine) {
            this.outLeadSine = outLeadSine;
            return this;
        }

        @Override
        public Result build() {
            return new HtSineResult(outSine, outLeadSine, outBegIdx, outNBElement);
        }
    }
}
