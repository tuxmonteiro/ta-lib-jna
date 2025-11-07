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

public enum MAType {
    TA_MAType_SMA("Sma", 0),
    TA_MAType_EMA("Ema", 1),
    TA_MAType_WMA("Wma", 2),
    TA_MAType_DEMA("Dema", 3),
    TA_MAType_TEMA("Tema", 4),
    TA_MAType_TRIMA("Trima", 5),
    TA_MAType_KAMA("Kama", 6),
    TA_MAType_MAMA("Mama", 7),
    TA_MAType_T3("T3", 8)
    ;

    private final String name;
    private final int idx;

    MAType(String name, int idx) {
        this.name = name;
        this.idx = idx;
    }

    public int idx() {
        return idx;
    }

    public String str() {
        return name;
    }
}
