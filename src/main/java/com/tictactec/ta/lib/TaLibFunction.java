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
import java.lang.reflect.Method;
import java.util.*;

/*
 * NOT EDIT - Auto generated using scripts/generate_TaLibFunction.sh
 */
public enum TaLibFunction {
    ACCBANDS(Accbands.class,BandsResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    ACOS(Acos.class,RealResult.class,int.class, int.class, double[].class),
    ADD(Add.class,RealResult.class,int.class, int.class, double[].class, double[].class),
    AD(Ad.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    AD_OSC(AdOsc.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, int.class, int.class),
    ADX(Adx.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    ADXR(Adxr.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    APO(Apo.class,RealResult.class,int.class, int.class, double[].class, int.class, int.class, int.class),
    AROON(Aroon.class,AroonResult.class,int.class, int.class, double[].class, double[].class, int.class),
    AROON_OSC(AroonOsc.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    ASIN(Asin.class,RealResult.class,int.class, int.class, double[].class),
    ATAN(Atan.class,RealResult.class,int.class, int.class, double[].class),
    ATR(Atr.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    AVG_DEV(AvgDev.class,RealResult.class,int.class, int.class, double[].class, int.class),
    AVG_PRICE(AvgPrice.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    BBANDS(Bbands.class,BandsResult.class,int.class, int.class, double[].class, int.class, double.class, double.class, int.class),
    BETA(Beta.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    BOP(Bop.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CCI(Cci.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    CDL_2CROWS(Cdl2Crows.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_3BLACKCROWS(Cdl3BlackCrows.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_3INSIDE(Cdl3Inside.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_3LINESTRIKE(Cdl3LineStrike.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_3OUTSIDE(Cdl3Outside.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_3STARSINSOUTH(Cdl3StarsInSouth.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_3WHITESOLDIERS(Cdl3WhiteSoldiers.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_ABANDONEDBABY(CdlAbandonedBaby.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_ADVANCEBLOCK(CdlAdvanceBlock.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_BELTHOLD(CdlBeltHold.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_BREAKAWAY(CdlBreakaway.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_CLOSINGMARUBOZU(CdlClosingMarubozu.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_CONCEALBABYSWALL(CdlConcealBabysWall.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_COUNTERATTACK(CdlCounterAttack.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_DARKCLOUDCOVER(CdlDarkCloudCover.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_DOJI(CdlDoji.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_DOJISTAR(CdlDojiStar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_DRAGONFLYDOJI(CdlDragonflyDoji.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_ENGULFING(CdlEngulfing.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_EVENINGDOJISTAR(CdlEveningDojiStar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_EVENINGSTAR(CdlEveningStar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_GAPSIDESIDEWHITE(CdlGapSideSideWhite.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_GRAVESTONEDOJI(CdlGravestoneDoji.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HAMMER(CdlHammer.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HANGINGMAN(CdlHangingMan.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HARAMICROSS(CdlHaramiCross.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HARAMI(CdlHarami.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HIGNWAVE(CdlHignWave.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HIKKAKE(CdlHikkake.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HIKKAKEMOD(CdlHikkakeMod.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_HOMINGPIGEON(CdlHomingPigeon.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_IDENTICAL3CROWS(CdlIdentical3Crows.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_INNECK(CdlInNeck.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_INVERTEDHAMMER(CdlInvertedHammer.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_KICKINGBYLENGTH(CdlKickingByLength.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_KICKING(CdlKicking.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_LADDERBOTTOM(CdlLadderBottom.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_LONGLEGGEDDOJI(CdlLongLeggedDoji.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_LONGLINE(CdlLongLine.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_MARUBOZU(CdlMarubozu.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_MATCHINGLOW(CdlMatchingLow.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_MATHOLD(CdlMatHold.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_MORNINGDOJISTAR(CdlMorningDojiStar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_MORNINGSTAR(CdlMorningStar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, double.class),
    CDL_ONNECK(CdlOnNeck.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_PIERCING(CdlPiercing.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_RICKSHAWMAN(CdlRickshawMan.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_RISEFALL3METHODS(CdlRiseFall3Methods.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_SEPERATINGLINES(CdlSeperatingLines.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_SHOOTINGSTAR(CdlShootingStar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_SHORTLINE(CdlShortLine.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_SPINNINGTOP(CdlSpinningTop.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_STALLEDPATTERN(CdlStalledPattern.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_STICKSANDWICH(CdlStickSandwich.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_TAKURI(CdlTakuri.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_TASUKIGAP(CdlTasukiGap.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_THRUSTING(CdlThrusting.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_TRISTAR(CdlTristar.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_UNIQUE3RIVER(CdlUnique3River.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_UPSIDEGAP2CROWS(CdlUpsideGap2Crows.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CDL_XSIDEGAP3METHODS(CdlXSideGap3Methods.class,IntegerResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class),
    CEIL(Ceil.class,RealResult.class,int.class, int.class, double[].class),
    CMO(Cmo.class,RealResult.class,int.class, int.class, double[].class, int.class),
    CORREL(Correl.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    COSH(Cosh.class,RealResult.class,int.class, int.class, double[].class),
    COS(Cos.class,RealResult.class,int.class, int.class, double[].class),
    DEMA(Dema.class,RealResult.class,int.class, int.class, double[].class, int.class),
    DIV(Div.class,RealResult.class,int.class, int.class, double[].class, double[].class),
    DX(Dx.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    EMA(Ema.class,RealResult.class,int.class, int.class, double[].class, int.class),
    EXP(Exp.class,RealResult.class,int.class, int.class, double[].class),
    FLOOR(Floor.class,RealResult.class,int.class, int.class, double[].class),
    HT_DCPERIOD(HtDcPeriod.class,RealResult.class,int.class, int.class, double[].class),
    HT_DCPHASE(HtDcPhase.class,RealResult.class,int.class, int.class, double[].class),
    HT_PHASOR(HtPhasor.class,HtPhasorResult.class,int.class, int.class, double[].class),
    HT_SINE(HtSine.class,HtSineResult.class,int.class, int.class, double[].class),
    HT_TRENDLINE(HtTrendline.class,RealResult.class,int.class, int.class, double[].class),
    HT_TRENDMODE(HtTrendMode.class,IntegerResult.class,int.class, int.class, double[].class),
    IMI(Imi.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    KAMA(Kama.class,RealResult.class,int.class, int.class, double[].class, int.class),
    LINEAR_REGANGLE(LinearRegAngle.class,RealResult.class,int.class, int.class, double[].class, int.class),
    LINEAR_REGINTERCEPT(LinearRegIntercept.class,RealResult.class,int.class, int.class, double[].class, int.class),
    LINEAR_REG(LinearReg.class,RealResult.class,int.class, int.class, double[].class, int.class),
    LINEAR_REGSLOPE(LinearRegSlope.class,RealResult.class,int.class, int.class, double[].class, int.class),
    LN(Ln.class,RealResult.class,int.class, int.class, double[].class),
    LOG_10(Log10.class,RealResult.class,int.class, int.class, double[].class),
    MACD_EXT(MacdExt.class,MACDResult.class,int.class, int.class, double[].class, int.class, int.class, int.class, int.class, int.class, int.class),
    MACD_FIX(MacdFix.class,MACDResult.class,int.class, int.class, double[].class, int.class),
    MACD(Macd.class,MACDResult.class,int.class, int.class, double[].class, int.class, int.class, int.class),
    MAMA(Mama.class,MamaResult.class,int.class, int.class, double[].class, double.class, double.class),
    MAX_INDEX(MaxIndex.class,IntegerResult.class,int.class, int.class, double[].class, int.class),
    MAX(Max.class,RealResult.class,int.class, int.class, double[].class, int.class),
    MED_PRICE(MedPrice.class,RealResult.class,int.class, int.class, double[].class, double[].class),
    MFI(Mfi.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, double[].class, int.class),
    MID_POINT(MidPoint.class,RealResult.class,int.class, int.class, double[].class, int.class),
    MID_PRICE(MidPrice.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    MIN_INDEX(MinIndex.class,IntegerResult.class,int.class, int.class, double[].class, int.class),
    MIN(Min.class,RealResult.class,int.class, int.class, double[].class, int.class),
    MIN_MAXINDEX(MinMaxIndex.class,MinMaxIdxResult.class,int.class, int.class, double[].class, int.class),
    MIN_MAX(MinMax.class,MinMaxResult.class,int.class, int.class, double[].class, int.class),
    MINUS_DI(MinusDI.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    MINUS_DM(MinusDM.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    MOM(Mom.class,RealResult.class,int.class, int.class, double[].class, int.class),
    MOVING_AVERAGE(MovingAverage.class,RealResult.class,int.class, int.class, double[].class, int.class, int.class),
    MOVING_AVERAGEVARIABLEPERIOD(MovingAverageVariablePeriod.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class, int.class, int.class),
    MULT(Mult.class,RealResult.class,int.class, int.class, double[].class, double[].class),
    NATR(Natr.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    OBV(Obv.class,RealResult.class,int.class, int.class, double[].class, double[].class),
    PLUS_DI(PlusDI.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    PLUS_DM(PlusDM.class,RealResult.class,int.class, int.class, double[].class, double[].class, int.class),
    PPO(Ppo.class,RealResult.class,int.class, int.class, double[].class, int.class, int.class, int.class),
    ROC(Roc.class,RealResult.class,int.class, int.class, double[].class, int.class),
    ROC_P(RocP.class,RealResult.class,int.class, int.class, double[].class, int.class),
    ROC_R100(RocR100.class,RealResult.class,int.class, int.class, double[].class, int.class),
    ROC_R(RocR.class,RealResult.class,int.class, int.class, double[].class, int.class),
    RSI(Rsi.class,RealResult.class,int.class, int.class, double[].class, int.class),
    SAR_EXT(SarExt.class,RealResult.class,int.class, int.class, double[].class, double[].class, double.class, double.class, double.class, double.class, double.class, double.class, double.class, double.class),
    SAR(Sar.class,RealResult.class,int.class, int.class, double[].class, double[].class, double.class, double.class),
    SINH(Sinh.class,RealResult.class,int.class, int.class, double[].class),
    SIN(Sin.class,RealResult.class,int.class, int.class, double[].class),
    SMA(Sma.class,RealResult.class,int.class, int.class, double[].class, int.class),
    SQRT(Sqrt.class,RealResult.class,int.class, int.class, double[].class),
    STD_DEV(StdDev.class,RealResult.class,int.class, int.class, double[].class, int.class, double.class),
    STOCH_F(StochF.class,FastResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class, int.class, int.class),
    STOCH(Stoch.class,SlowResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class, int.class, int.class, int.class, int.class),
    STOCH_RSI(StochRsi.class,FastResult.class,int.class, int.class, double[].class, int.class, int.class, int.class, int.class),
    SUB(Sub.class,RealResult.class,int.class, int.class, double[].class, double[].class),
    SUM(Sum.class,RealResult.class,int.class, int.class, double[].class, int.class),
    T3(T3.class,RealResult.class,int.class, int.class, double[].class, int.class, double.class),
    TANH(Tanh.class,RealResult.class,int.class, int.class, double[].class),
    TAN(Tan.class,RealResult.class,int.class, int.class, double[].class),
    TEMA(Tema.class,RealResult.class,int.class, int.class, double[].class, int.class),
    TRIMA(Trima.class,RealResult.class,int.class, int.class, double[].class, int.class),
    TRIX(Trix.class,RealResult.class,int.class, int.class, double[].class, int.class),
    TRUE_RANGE(TrueRange.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class),
    TSF(Tsf.class,RealResult.class,int.class, int.class, double[].class, int.class),
    TYP_PRICE(TypPrice.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class),
    ULT_OSC(UltOsc.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class, int.class, int.class),
    VARIANCE(Variance.class,RealResult.class,int.class, int.class, double[].class, int.class, double.class),
    WCL_PRICE(WclPrice.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class),
    WILL_R(WillR.class,RealResult.class,int.class, int.class, double[].class, double[].class, double[].class, int.class),
    WMA(Wma.class,RealResult.class,int.class, int.class, double[].class, int.class),

    UNDEF(null, null),
    ;

    private static final Map<String, TaLibFunction> enumMap = new HashMap<>();

    static {
        List.of(values()).forEach(e -> enumMap.put(e.name(), e));
    }

    public static TaLibFunction byName(String name) {
        if (name == null) {
            return UNDEF;
        }
        return Optional.ofNullable(enumMap.get(name)).orElse(UNDEF);
    }

    private final Class<? extends Result> resultClass;
    private final LinkedList<Object> params = new LinkedList<>();

    private final Method taLibStaticMethodExecute;

    TaLibFunction(Class<?> taClass, Class<? extends Result> resultClass, Class<?>... paramsType) {
        this.resultClass = resultClass;

        try {
            if (taClass != null) {
                this.taLibStaticMethodExecute = taClass.getMethod("execute", paramsType);
            } else {
                this.taLibStaticMethodExecute = null;
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public TaLibFunction params(Object[] params) {
        this.params.clear();
        if (params.length >= 3 && params[2] instanceof double[] firstDoubleArray) {
            if (params[1] instanceof Integer endIdx) {
                params[1] = (endIdx == 0) ? firstDoubleArray.length - 1 : endIdx;
            }
        }
        for (Object param : params) {
            this.params.addLast(param);
        }
        return this;
    }

    public Result execute() {
        if (this == UNDEF) {
            throw new IllegalArgumentException("TaLib Function is UNDEF");
        }
        try {
            var resultObj = taLibStaticMethodExecute.invoke(null, params.toArray());
            if (resultClass.isInstance(resultObj)) {
                return resultClass.cast(resultObj);
            }
            throw new RuntimeException(this + ": method not return " + resultClass.getSimpleName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public TALibBuilder builder() {
        return new TALibBuilder(this);
    }
}
