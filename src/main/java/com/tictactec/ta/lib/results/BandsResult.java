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

public class BandsResult extends Result {
    private final double[] outRealUpperBand;
    private final double[] outRealMiddleBand;
    private final double[] outRealLowerBand;


    public BandsResult(double[] outRealUpperBand, double[] outRealMiddleBand, double[] outRealLowerBand, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outRealUpperBand = outRealUpperBand;
        this.outRealMiddleBand = outRealMiddleBand;
        this.outRealLowerBand = outRealLowerBand;
    }

    public double[] outRealUpperBand() {
        return outRealUpperBand;
    }

    public double[] outRealMiddleBand() {
        return outRealMiddleBand;
    }

    public double[] outRealLowerBand() {
        return outRealLowerBand;
    }

    public static BandsResultBuilder builder() {
        return new BandsResultBuilder();
    }

    public static class BandsResultBuilder extends Builder {
        private double[] outRealUpperBand;
        private double[] outRealMiddleBand;
        private double[] outRealLowerBand;

        @Override
        public Builder outNBElement(int outBegIdx) {
            return super.outNBElement(outBegIdx);
        }

        @Override
        public Builder outBegIdx(int outNBElement) {
            return super.outBegIdx(outNBElement);
        }

        public BandsResultBuilder outRealUpperBand(double[] outRealUpperBand) {
            this.outRealUpperBand = outRealUpperBand;
            return this;
        }

        public BandsResultBuilder outRealMiddleBand(double[] outRealMiddleBand) {
            this.outRealMiddleBand = outRealMiddleBand;
            return this;
        }

        public BandsResultBuilder outRealLowerBand(double[] outRealLowerBand) {
            this.outRealLowerBand = outRealLowerBand;
            return this;
        }

        @Override
        public Result build() {
            return new BandsResult(outRealUpperBand, outRealMiddleBand, outRealLowerBand, outBegIdx, outNBElement);
        }
    }
}
