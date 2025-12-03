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

package com.tictactec.ta.lib;

import com.tictactec.ta.lib.results.Result;

import java.util.LinkedList;

public class TALibBuilder {
    private final TaLibFunction taLibFunction;
    private final int[] ints = new int[]{
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            Integer.MIN_VALUE
    };
    private final double[][] doubleArrays = new double[][]{
            new double[0],
            new double[0],
            new double[0],
            new double[0],
            new double[0]
    };
    private final double[] doubles = new double[]{
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Double.MIN_VALUE,
            Double.MIN_VALUE,
    };

    protected TALibBuilder(TaLibFunction taLibFunction) {
        this.taLibFunction = taLibFunction;
    }

    public TaLibFunction taLibFunction() {
        return taLibFunction;
    }

    public TALibBuilder startIdx(int startIdx) {
        this.ints[0] = startIdx;
        return this;
    }

    public TALibBuilder endIdx(int endIdx) {
        this.ints[1] = endIdx;
        return this;
    }

    public TALibBuilder optInTimePeriod(int optInTimePeriod) {
        this.ints[2] = optInTimePeriod;
        return this;
    }

    public TALibBuilder optInMAType(MAType maType) {
        this.ints[3] = maType.idx();
        return this;
    }

    public TALibBuilder inReal(double[] inreal) {
        this.doubleArrays[0] = inreal;
        return this;
    }

    public TALibBuilder inPeriods(double[] inperiods) {
        this.doubleArrays[1] = inperiods;
        return this;
    }

    public TALibBuilder inReal0(double[] inreal0) {
        this.doubleArrays[0] = inreal0;
        return this;
    }

    public TALibBuilder inReal1(double[] inreal1) {
        this.doubleArrays[1] = inreal1;
        return this;
    }

    public TALibBuilder open(double[] open) {
        this.doubleArrays[0] = open;
        return this;
    }

    public TALibBuilder high(double[] high) {
        this.doubleArrays[1] = high;
        return this;
    }

    public TALibBuilder low(double[] low) {
        this.doubleArrays[2] = low;
        return this;
    }

    public TALibBuilder close(double[] close) {
        this.doubleArrays[3] = close;
        return this;
    }

    public TALibBuilder volume(double[] volume) {
        this.doubleArrays[4] = volume;
        return this;
    }

    public TALibBuilder optInDeviationsup(double optInDeviationsup) {
        this.doubles[0] = optInDeviationsup;
        return this;
    }

    public TALibBuilder optInDeviationsdown(double optInDeviationsdown) {
        this.doubles[1] = optInDeviationsdown;
        return this;
    }

    public Result execute() {
        defragDoubleArrays();
        defragDoubles();
        defragInts();

        LinkedList<Object> params = new LinkedList<>();
        processParams(params);

        if (params.size() >= 3) {
            return taLibFunction.params(params.toArray()).execute();
        }
        throw new RuntimeException("params incorrect or insuficcients");
    }

    private void processParams(LinkedList<Object> params) {
        params.addLast(ints[0]); // startIdx
        params.addLast(ints[1]); // endIdx

        for (int x = 0; x < doubleArrays.length - 1; x++) {
            if (doubleArrays[x].length > 0) params.addLast(doubleArrays[x]);
        }
        if (ints[2] > Integer.MIN_VALUE) {
            params.addLast(ints[2]);
        }
        if (doubles[0] > Double.MIN_VALUE) {
            for (int x = 0; x < doubles.length - 1; x++) {
                if (doubles[x] > Double.MIN_VALUE) params.addLast(doubles[x]);
            }
        }
        for (int x = 3; x < ints.length - 1; x++) {
            if (ints[x] > Integer.MIN_VALUE) params.addLast(ints[x]);
        }
    }

    private void defragDoubleArrays() {
        int w = 0;
        while (w < doubleArrays.length && doubleArrays[w].length == 0) {
            int k = w + 1;
            while (k < doubleArrays.length - 1 && doubleArrays[k].length == 0) k++;
            if (k == doubleArrays.length) break;
            if (doubleArrays[k].length != 0) {
                doubleArrays[w] = doubleArrays[k];
                doubleArrays[k] = new double[0];
            }
            w++;
        }
    }

    private void defragDoubles() {
        int w = 0;
        while (w < doubles.length && doubles[w] == Double.MIN_VALUE) {
            int k = w + 1;
            while (k < doubles.length - 1 && doubles[k] == Double.MIN_VALUE) k++;
            if (k == doubles.length) break;
            if (doubles[k] > Double.MIN_VALUE) {
                doubles[w] = doubles[k];
                doubles[k] = Double.MIN_VALUE;
            }
            w++;
        }
    }

    private void defragInts() {
        int w = 0;
        while (w < ints.length && ints[w] == Integer.MIN_VALUE) {
            int k = w + 1;
            while (k < ints.length - 1 && ints[k] == Integer.MIN_VALUE) k++;
            if (k == ints.length) break;
            if (ints[k] > Integer.MIN_VALUE) {
                ints[w] = ints[k];
                ints[k] = Integer.MIN_VALUE;
            }
            w++;
        }
    }
}
