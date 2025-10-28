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

public class AroonResult extends Result {
    private final double[] outAroonDown;
    private final double[] outAroonUp;
    
    public AroonResult(double[] outAroonDown, double[] outAroonUp, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outAroonDown = outAroonDown;
        this.outAroonUp = outAroonUp;
    }

    public double[] outAroonDown() {
        return outAroonDown;
    }

    public double[] outAroonUp() {
        return outAroonUp;
    }

    public static Builder builder() {
        return new AroonResultBuilder();
    }

    public static class AroonResultBuilder extends Builder {
        private double[] outAroonDown;
        private double[] outAroonUp;

        @Override
        public Builder setOutBegIdx(int outBegIdx) {
            return super.setOutBegIdx(outBegIdx);
        }

        @Override
        public Builder setOutNBElement(int outNBElement) {
            return super.setOutNBElement(outNBElement);
        }

        public Builder setOutAroonDown(double[] outAroonDown) {
            this.outAroonDown = outAroonDown;
            return this;
        }

        public Builder setOutAroonUp(double[] outAroonUp) {
            this.outAroonUp = outAroonUp;
            return this;
        }

        @Override
        public Result build() {
            return new AroonResult(outAroonDown, outAroonUp, outBegIdx, outNBElement);
        }
    }
}
