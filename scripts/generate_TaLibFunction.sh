#!env bash

#
#  Copyright (c) 2025 fibonsai.com
#  All rights reserved.
#
#  This source is subject to the Apache License, Version 2.0.
#  Please see the LICENSE file for more information.
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.
#

cat <<EOF > src/main/java/com/tictactec/ta/lib/TaLibFunction.java
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
EOF


x=0
IFS=$'\n'

for z in `grep 'execute(\|Result result =' src/main/java/com/tictactec/ta/lib/functions/*java | sed 's|src/main/java/com/tictactec/ta/lib/functions/||' | sed 's/public static Result execute(//;s/) throws.*/:/;s/.*Result result = //;s/\(.*\)\.builder.*/\1.class/'`
do
  x=$[x+1]
  w=$[x%2]
  [ "$w" = "1" ] && echo -n "$z" || echo "$z"
done | sed 's/int [^,]\+\([,:]\)/int.class\1/g;s/int\[\] [^,]\+\([,:]\)/int[].class\1/g;s/double [^,]\+\([,:]\)/double.class\1/g;s/double\[\] [^,]\+\([,:]\)/double[].class\1/g;s/.java:    /.java:/' \
  | awk -F':' '{print $1"("$1","$3","$2"),"}'| sed 's/.java//' \
  | sed 's/\([A-Z]\)\([a-z]\+\)\([0-9A-Z]\)/\1\2_\3/;s/_Result/Result/;s/\.java/.class/;s/^/    /' \
  | awk -F'(' '{ print toupper($1)"("$2 }' >> src/main/java/com/tictactec/ta/lib/TaLibFunction.java

cat <<EOF >> src/main/java/com/tictactec/ta/lib/TaLibFunction.java

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
EOF
