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

public class MACDResult extends Result {
    private final double[] outMACD;
    private final double[] outMACDSignal;
    private final double[] outMACDHist;


    public MACDResult(double[] outMACD, double[] outMACDSignal, double[] outMACDHist, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outMACD = outMACD;
        this.outMACDSignal = outMACDSignal;
        this.outMACDHist = outMACDHist;
    }

    public double[] outMACD() {
        return outMACD;
    }

    public double[] outMACDSignal() {
        return outMACDSignal;
    }

    public double[] outMACDHist() {
        return outMACDHist;
    }

    public static MACDResultBuilder builder() {
        return new MACDResultBuilder();
    }

    public static class MACDResultBuilder extends Builder {
        private double[] outMACD;
        private double[] outMACDSignal;
        private double[] outMACDHist;

        @Override
        public Builder outNBElement(int outBegIdx) {
            return super.outNBElement(outBegIdx);
        }

        @Override
        public Builder outBegIdx(int outNBElement) {
            return super.outBegIdx(outNBElement);
        }

        public MACDResultBuilder outMACD(double[] outMACD) {
            this.outMACD = outMACD;
            return this;
        }

        public MACDResultBuilder outMACDSignal(double[] outMACDSignal) {
            this.outMACDSignal = outMACDSignal;
            return this;
        }

        public MACDResultBuilder outMACDHist(double[] outMACDHist) {
            this.outMACDHist = outMACDHist;
            return this;
        }

        @Override
        public Result build() {
            return new MACDResult(outMACD, outMACDSignal, outMACDHist, outBegIdx, outNBElement);
        }
    }
}
