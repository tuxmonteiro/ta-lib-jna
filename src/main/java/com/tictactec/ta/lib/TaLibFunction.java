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

import com.tictactec.ta.lib.functions.*;
import com.tictactec.ta.lib.results.*;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public enum TaLibFunction {
    // ArooResult
    AROON(Aroon.class, AroonResult.class),

    // BandsResult
    ACC_BANDS(Accbands.class, BandsResult.class),
    BBANDS(Bbands.class, BandsResult.class),

    // FastResult
    STOCHF(StochF.class, FastResult.class),
    STOCH_RSI(StochRsi.class, FastResult.class),

    // IntegerResult
    CDL_2CROWS(Cdl2Crows.class, IntegerResult.class),
    CDL_3BLACKCROWS(Cdl3BlackCrows.class, IntegerResult.class),
    CDL_3INSIDE(Cdl3Inside.class, IntegerResult.class),
    CDL_3LINESTRIKE(Cdl3LineStrike.class, IntegerResult.class),
    CDL_3OUTSIDE(Cdl3Outside.class, IntegerResult.class),
    CDL_3STARSINSOUTH(Cdl3StarsInSouth.class, IntegerResult.class),
    CDL_3WHITESOLDIERS(Cdl3WhiteSoldiers.class, IntegerResult.class),
    CDL_ABANDONEDBABY(CdlAbandonedBaby.class, IntegerResult.class),
    CDL_ADVANCEBLOCK(CdlAdvanceBlock.class, IntegerResult.class),
    CDL_BELTHOLD(CdlBeltHold.class, IntegerResult.class),
    CDL_BREAKAWAY(CdlBreakaway.class, IntegerResult.class),
    CDL_CLOSINGMARUBOZU(CdlClosingMarubozu.class, IntegerResult.class),
    CDL_CONCEALBABYSWALL(CdlConcealBabysWall.class, IntegerResult.class),
    CDL_COUNTERATTACK(CdlCounterAttack.class, IntegerResult.class),
    CDL_DARKCLOUDCOVER(CdlDarkCloudCover.class, IntegerResult.class),
    CDL_DOJI(CdlDoji.class, IntegerResult.class),
    CDL_DOJISTAR(CdlDojiStar.class, IntegerResult.class),
    CDL_DRAGONFLYDOJI(CdlDragonflyDoji.class, IntegerResult.class),
    CDL_ENGULFING(CdlEngulfing.class, IntegerResult.class),
    CDL_EVENINGDOJISTAR(CdlEveningDojiStar.class, IntegerResult.class),
    CDL_EVENINGSTAR(CdlEveningStar.class, IntegerResult.class),
    CDL_GAPSIDESIDEWHITE(CdlGapSideSideWhite.class, IntegerResult.class),
    CDL_GRAVESTONEDOJI(CdlGravestoneDoji.class, IntegerResult.class),
    CDL_HAMMER(CdlHammer.class, IntegerResult.class),
    CDL_HANGINGMAN(CdlHangingMan.class, IntegerResult.class),
    CDL_HARAMICROSS(CdlHaramiCross.class, IntegerResult.class),
    CDL_HARAMI(CdlHarami.class, IntegerResult.class),
    CDL_HIGNWAVE(CdlHignWave.class, IntegerResult.class),
    CDL_HIKKAKE(CdlHikkake.class, IntegerResult.class),
    CDL_HIKKAKEMOD(CdlHikkakeMod.class, IntegerResult.class),
    CDL_HOMINGPIGEON(CdlHomingPigeon.class, IntegerResult.class),
    CDL_IDENTICAL3CROWS(CdlIdentical3Crows.class, IntegerResult.class),
    CDL_INNECK(CdlInNeck.class, IntegerResult.class),
    CDL_INVERTEDHAMMER(CdlInvertedHammer.class, IntegerResult.class),
    CDL_KICKINGBYLENGTH(CdlKickingByLength.class, IntegerResult.class),
    CDL_KICKING(CdlKicking.class, IntegerResult.class),
    CDL_LADDERBOTTOM(CdlLadderBottom.class, IntegerResult.class),
    CDL_LONGLEGGEDDOJI(CdlLongLeggedDoji.class, IntegerResult.class),
    CDL_LONGLINE(CdlLongLine.class, IntegerResult.class),
    CDL_MARUBOZU(CdlMarubozu.class, IntegerResult.class),
    CDL_MATCHINGLOW(CdlMatchingLow.class, IntegerResult.class),
    CDL_MATHOLD(CdlMatHold.class, IntegerResult.class),
    CDL_MORNINGDOJISTAR(CdlMorningDojiStar.class, IntegerResult.class),
    CDL_MORNINGSTAR(CdlMorningStar.class, IntegerResult.class),
    CDL_ONNECK(CdlOnNeck.class, IntegerResult.class),
    CDL_PIERCING(CdlPiercing.class, IntegerResult.class),
    CDL_RICKSHAWMAN(CdlRickshawMan.class, IntegerResult.class),
    CDL_RISEFALL3METHODS(CdlRiseFall3Methods.class, IntegerResult.class),
    CDL_SEPERATINGLINES(CdlSeperatingLines.class, IntegerResult.class),
    CDL_SHOOTINGSTAR(CdlShootingStar.class, IntegerResult.class),
    CDL_SHORTLINE(CdlShortLine.class, IntegerResult.class),
    CDL_SPINNINGTOP(CdlSpinningTop.class, IntegerResult.class),
    CDL_STALLEDPATTERN(CdlStalledPattern.class, IntegerResult.class),
    CDL_STICKSANDWICH(CdlStickSandwich.class, IntegerResult.class),
    CDL_TAKURI(CdlTakuri.class, IntegerResult.class),
    CDL_TASUKIGAP(CdlTasukiGap.class, IntegerResult.class),
    CDL_THRUSTING(CdlThrusting.class, IntegerResult.class),
    CDL_TRISTAR(CdlTristar.class, IntegerResult.class),
    CDL_UNIQUE3RIVER(CdlUnique3River.class, IntegerResult.class),
    CDL_UPSIDEGAP2CROWS(CdlUpsideGap2Crows.class, IntegerResult.class),
    CDL_XSIDEGAP3METHODS(CdlXSideGap3Methods.class, IntegerResult.class),
    HT_TRENDMODE(HtTrendMode.class, IntegerResult.class),
    MAXINDEX(MaxIndex.class, IntegerResult.class),

    // HtPhasorResult
    HT_PHASOR(HtPhasor.class, HtPhasorResult.class),

    // HtSineResult
    HT_SINE(HtSine.class, HtSineResult.class),

    // MACDResult
    MACD_EXT(MacdExt.class, MACDResult.class),
    MACD_FIX(MacdFix.class, MACDResult.class),
    MACD(Macd.class, MACDResult.class),

    // MinMaxResult
    MINMAX(MinMax.class, MinMaxResult.class),

    // MinMaxIdxResult
    MINMAX_INDEX(MinMaxIndex.class, MinMaxIdxResult.class),

    // RealResult
    ACOS(Acos.class, RealResult.class),
    ADD(Add.class, RealResult.class),
    AD(Ad.class, RealResult.class),
    AD_OSC(AdOsc.class, RealResult.class),
    ADX(Adx.class, RealResult.class),
    ADXR(Adxr.class, RealResult.class),
    APO(Apo.class, RealResult.class),
    AROON_OSC(AroonOsc.class, RealResult.class),
    ASIN(Asin.class, RealResult.class),
    ATAN(Atan.class, RealResult.class),
    ATR(Atr.class, RealResult.class),
    AVGDEV(AvgDev.class, RealResult.class),
    AVGPRICE(AvgPrice.class, RealResult.class),
    BETA(Beta.class, RealResult.class),
    BOP(Bop.class, RealResult.class),
    CCI(Cci.class, RealResult.class),
    CEIL(Ceil.class, RealResult.class),
    CMO(Cmo.class, RealResult.class),
    CORREL(Correl.class, RealResult.class),
    COSH(Cosh.class, RealResult.class),
    COS(Cos.class, RealResult.class),
    DEMA(Dema.class, RealResult.class),
    DIV(Div.class, RealResult.class),
    DX(Dx.class, RealResult.class),
    EMA(Ema.class, RealResult.class),
    EXP(Exp.class, RealResult.class),
    FLOOR(Floor.class, RealResult.class),
    HTDCPERIOD(HtDcPeriod.class, RealResult.class),
    HTDCPHASE(HtDcPhase.class, RealResult.class),
    HT_TRENDLINE(HtTrendline.class, RealResult.class),
    IMI(Imi.class, RealResult.class),
    KAMA(Kama.class, RealResult.class),
    LINEAR_REG_ANGLE(LinearRegAngle.class, RealResult.class),
    LINEAR_REG_INTERCEPT(LinearRegIntercept.class, RealResult.class),
    LINEAR_REG(LinearReg.class, RealResult.class),
    LINEAR_REG_SLOPE(LinearRegSlope.class, RealResult.class),
    LN(Ln.class, RealResult.class),
    LOG10(Log10.class, RealResult.class),
    MAMA(Mama.class, RealResult.class),
    MAX(Max.class, RealResult.class),
    MEDPRICE(MedPrice.class, RealResult.class),
    MFI(Mfi.class, RealResult.class),
    MIDPOINT(MidPoint.class, RealResult.class),
    MIDPRICE(MidPrice.class, RealResult.class),
    MININDEX(MinIndex.class, RealResult.class),
    MIN(Min.class, RealResult.class),
    MINUS_DI(MinusDI.class, RealResult.class),
    MINUS_DM(MinusDM.class, RealResult.class),
    MOM(Mom.class, RealResult.class),
    MOVING_AVERAGE(MovingAverage.class, RealResult.class),
    MOVING_AVERAGE_VARIABLE_PERIOD(MovingAverageVariablePeriod.class, RealResult.class),
    MULT(Mult.class, RealResult.class),
    NATR(Natr.class, RealResult.class),
    OBV(Obv.class, RealResult.class),
    PLUS_DI(PlusDI.class, RealResult.class),
    PLUS_DM(PlusDM.class, RealResult.class),
    PPO(Ppo.class, RealResult.class),
    ROC(Roc.class, RealResult.class),
    ROCP(RocP.class, RealResult.class),
    ROCR100(RocR100.class, RealResult.class),
    ROCR(RocR.class, RealResult.class),
    RSI(Rsi.class, RealResult.class),
    SAR_EXT(SarExt.class, RealResult.class),
    SAR(Sar.class, RealResult.class),
    SINH(Sinh.class, RealResult.class),
    SIN(Sin.class, RealResult.class),
    SMA(Sma.class, RealResult.class),
    SQRT(Sqrt.class, RealResult.class),
    STDDEV(StdDev.class, RealResult.class),
    SUB(Sub.class, RealResult.class),
    SUM(Sum.class, RealResult.class),
    T3(T3.class, RealResult.class),
    TANH(Tanh.class, RealResult.class),
    TAN(Tan.class, RealResult.class),
    TEMA(Tema.class, RealResult.class),
    TRIMA(Trima.class, RealResult.class),
    TRIX(Trix.class, RealResult.class),
    TRUE_RANGE(TrueRange.class, RealResult.class),
    TSF(Tsf.class, RealResult.class),
    TYPPRICE(TypPrice.class, RealResult.class),
    ULT_OSC(UltOsc.class, RealResult.class),
    VARIANCE(Variance.class, RealResult.class),
    WCLPRICE(WclPrice.class, RealResult.class),
    WILLR(WillR.class, RealResult.class),
    WMA(Wma.class, RealResult.class),

    // SlowResult
    STOCH(Stoch.class, SlowResult.class)
    ;

    private final Class<?> taClass;
    private final Class<? extends Result> resultClass;
    private final LinkedList<Object> _params = new LinkedList<>();

    TaLibFunction(Class<?> taClass, Class<? extends Result> resultClass) {
        this.taClass = taClass;
        this.resultClass = resultClass;
    }

    public Class<?> taClass() {
        return taClass;
    }

    public Class<? extends Result> resultClass() {
        return resultClass;
    }

    public TaLibFunction params(Object[] params) {
        this._params.clear();
        if (params[2] instanceof Double[] firstDoubleArray) {
            if (params[1] instanceof Integer endIdx) {
                params[1] = (endIdx == 0) ? firstDoubleArray.length - 1 : endIdx;
            }
        }
        for (Object param : params) {
            this._params.addLast(param);
        }
        return this;
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    private Class<?>[] paramsType() {
        return _params.stream().map(o -> {
            try {
                return o.getClass().getField("TYPE").get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return o.getClass();
            }
        }).toList().toArray(new Class<?>[0]);
    }

    public Result execute() {
        try {
            var resObj = taClass()
                    .getMethod("execute", paramsType())
                    .invoke(null, _params.toArray());
            if (resultClass().isInstance(resObj)) {
                return resultClass().cast(resObj);
            }
            throw new RuntimeException(this + ": method not return " + resultClass().getSimpleName());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Builder builder() {
        return new Builder(this);
    }

    public static class Builder {
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

        protected Builder(TaLibFunction taLibFunction) {
            this.taLibFunction = taLibFunction;
        }

        public Builder startIdx(int startIdx) {
            this.ints[0] = startIdx;
            return this;
        }

        public Builder endIdx(int endIdx) {
            this.ints[1] = endIdx;
            return this;
        }

        public Builder optInTimePeriod(int optInTimePeriod) {
            this.ints[2] = optInTimePeriod;
            return this;
        }

        public Builder inReal(double[] inreal) {
            this.doubleArrays[0] = inreal;
            return this;
        }

        public Builder inPeriods(double[] inperiods) {
            this.doubleArrays[1] = inperiods;
            return this;
        }

        public Builder inReal0(double[] inreal0) {
            this.doubleArrays[0] = inreal0;
            return this;
        }

        public Builder inReal1(double[] inreal1) {
            this.doubleArrays[1] = inreal1;
            return this;
        }

        public Builder open(double[] open) {
            this.doubleArrays[0] = open;
            return this;
        }

        public Builder high(double[] high) {
            this.doubleArrays[1] = high;
            return this;
        }

        public Builder low(double[] low) {
            this.doubleArrays[2] = low;
            return this;
        }

        public Builder close(double[] close) {
            this.doubleArrays[3] = close;
            return this;
        }

        public Builder volume(double[] volume) {
            this.doubleArrays[4] = volume;
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
}
