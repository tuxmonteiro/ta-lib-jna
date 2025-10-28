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

public class IntegerResult extends Result {
    public final int[] outInteger;

    public IntegerResult(int[] outInteger, int outNBElement, int outBegIdx) {
        super(outNBElement, outBegIdx);
        this.outInteger = outInteger;
    }

    public int[] outInteger() {
        return outInteger;
    }

    public static IntegerResultBuilder builder() {
        return new IntegerResultBuilder();
    }

    public static class IntegerResultBuilder extends Builder {
        public int[] outInteger;

        @Override
        public Builder outNBElement(int outBegIdx) {
            return super.outNBElement(outBegIdx);
        }

        @Override
        public Builder outBegIdx(int outNBElement) {
            return super.outBegIdx(outNBElement);
        }

        public IntegerResultBuilder outInteger(int[] outInteger) {
            this.outInteger = outInteger;
            return this;
        }

        @Override
        public Result build() {
            return new IntegerResult(outInteger, outBegIdx, outNBElement);
        }
    }
}
