#   Copyright (c) 2025 fibonsai.com
#   All rights reserved.
#
#   This source is subject to the Apache License, Version 2.0.
#   Please see the LICENSE file for more information.
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.

import os
import xml.etree.ElementTree as ET

def create_maven_project(xml_file):
    """
    Generates a complete Maven project for ta-lib based on the provided XML definition.
    """
    base_dir = '.'

    # Create directory structure
    main_java_dir = os.path.join(base_dir, 'src/main/java/com/tictactec/ta/lib')
    test_java_dir = os.path.join(base_dir, 'src/test/java/com/tictactec/ta/lib')
    os.makedirs(main_java_dir, exist_ok=True)
    os.makedirs(test_java_dir, exist_ok=True)

    # Parse the XML file
    tree = ET.parse(xml_file)
    root = tree.getroot()
    functions = list(root.findall('FinancialFunction'))

    # Create TALib.java core interface
    create_core_lib_class(main_java_dir, functions)

    # Create a class for each function and its test
    for func in functions:
        create_function_class(main_java_dir, func)
        create_test_class(test_java_dir, func)

    print(f"Maven project created")

def get_java_type(ta_type):
    """Maps TA-Lib XML types to Java types."""
    if ta_type in ['Integer', 'MA Type']:
        return 'int'
    if ta_type == 'Double':
        return 'double'
    if 'Array' in ta_type:
        if 'Integer' in ta_type:
            return 'int[]'
        return 'double[]'
    return 'double[]' # Default for High, Low, Open, Close, Volume

def get_jna_type(ta_type):
    """Maps TA-Lib XML types to JNA interface types."""
    if ta_type in ['Integer', 'MA Type']:
        return 'int'
    if ta_type == 'Double':
        return 'double'
    if 'Array' in ta_type:
        if 'Integer' in ta_type:
            return 'int[]'
        return 'double[]'
    return 'double[]'

def create_core_lib_class(main_java_dir, functions):
    """
    Creates the core TALib.java interface for JNA.
    """
    content = [
        "package com.tictactec.ta.lib;",
        "",
        "import com.sun.jna.Library;",
        "import com.sun.jna.Native;",
        "import com.sun.jna.ptr.IntByReference;",
        "",
        "public interface TALib extends Library {",
        "    TALib INSTANCE = Native.load(\"ta-lib\", TALib.class);",
        "",
        "    // Core functions",
        "    int TA_Initialize();",
        "    int TA_Shutdown();",
        ""
    ]

    for func in functions:
        abbr = func.find('Abbreviation').text

        params = ["int startIdx", "int endIdx"]

        # Required Inputs
        req_inputs = func.find('RequiredInputArguments')
        if req_inputs is not None:
            for arg in req_inputs.findall('RequiredInputArgument'):
                arg_type = arg.find('Type').text
                arg_name = arg.find('Name').text.lower()
                params.append(f"{get_jna_type(arg_type)} {arg_name}")

        # Optional Inputs
        opt_inputs = func.find('OptionalInputArguments')
        if opt_inputs is not None:
            for arg in opt_inputs.findall('OptionalInputArgument'):
                arg_type = arg.find('Type').text
                arg_name = "optIn" + arg.find('Name').text.replace(' ', '').replace('-', '')
                params.append(f"{get_jna_type(arg_type)} {arg_name}")

        params.extend(["IntByReference outBegIdx", "IntByReference outNBElement"])

        # Outputs
        outputs = func.find('OutputArguments')
        if outputs is not None:
            for arg in outputs.findall('OutputArgument'):
                arg_type = arg.find('Type').text
                arg_name = arg.find('Name').text
                params.append(f"{get_jna_type(arg_type)} {arg_name}")

        content.append(f"    int TA_{abbr}({', '.join(params)});")

    content.append("}")

    with open(os.path.join(main_java_dir, 'TALib.java'), 'w') as f:
        f.write('\n'.join(content))


def create_function_class(main_java_dir, func):
    """
    Creates a Java class for a single TA-Lib function.
    """
    camel_case_name = func.find('CamelCaseName').text
    abbr = func.find('Abbreviation').text
    short_desc = func.find('ShortDescription').text

    # Build parameter lists
    required_params = []
    optional_params = []
    output_params = []

    req_inputs = func.find('RequiredInputArguments')
    if req_inputs is not None:
        for arg in req_inputs.findall('RequiredInputArgument'):
            p_type = get_java_type(arg.find('Type').text)
            p_name = arg.find('Name').text.lower()
            required_params.append({'type': p_type, 'name': p_name})

    opt_inputs = func.find('OptionalInputArguments')
    if opt_inputs is not None:
        for arg in opt_inputs.findall('OptionalInputArgument'):
            p_type = get_java_type(arg.find('Type').text)
            p_name = "optIn" + arg.find('Name').text.replace(' ', '').replace('-', '')
            default_val = arg.find('DefaultValue').text
            optional_params.append({'type': p_type, 'name': p_name, 'default': default_val})

    outputs = func.find('OutputArguments')
    result_class = ''
    if outputs is not None:
        for arg in outputs.findall('OutputArgument'):
            p_type = get_java_type(arg.find('Type').text)
            p_name = arg.find('Name').text
            output_params.append({'type': p_type, 'name': p_name})
            if result_class == '':
                match p_name:
                    case "outReal":
                        result_class = 'RealResult'
                    case "outInteger":
                        result_class = 'IntegerResult'
                    case "outAroonDown" | "outAroonUp":
                        result_class = 'AroonResult'
                    case "outRealUpperBand" | "outRealMiddleBand" | "outRealLowerBand":
                        result_class = 'BandsResult'
                    case "outInPhase" | "outQuadrature":
                        result_class = 'HtPhasorResult'
                    case "outSine" | "outLeadSine":
                        result_class = 'HtSineResult'
                    case "outMACD" | "outMACDSignal" | "outMACDHist":
                        result_class = 'MACDResult'
                    case "outMAMA" | "outFAMA":
                        result_class = 'MamaResult'
                    case "outMin" | "outMax":
                        result_class = 'MinMaxResult'
                    case "outMinIdx" | "outMaxIdx":
                        result_class = 'MinMaxIdxResult'
                    case "outSlowK" | "outSlowD":
                        result_class = 'SlowResult'
                    case "outFastK" | "outFastD":
                        result_class = 'FastResult'

    # Start building the class content
    content = [
        f"package com.tictactec.ta.lib;",
        "",
        "import com.sun.jna.ptr.IntByReference;",
        "import org.slf4j.Logger;",
        "import org.slf4j.LoggerFactory;",
        "",
        "import com.tictactec.ta.lib.results.*;",
        "",
        f"/**",
        f" * This class is a wrapper for the TA-Lib function {abbr}: {short_desc}.",
        f" */",
        f"public class {camel_case_name} {{",
        "",
        f"    private static final Logger logger = LoggerFactory.getLogger({camel_case_name}.class);",
        "    private static final TALib taLib = TALib.INSTANCE;",
        ""
    ]

    # Create the main execute method
    method_params = ["int startIdx", "int endIdx"]
    method_params.extend([f"{p['type']} {p['name']}" for p in required_params])
    method_params.extend([f"{p['type']} {p['name']}" for p in optional_params])

    content.append(f"    public static Result execute({', '.join(method_params)}) throws ArithmeticException, IndexOutOfBoundsException {{")
    content.append(f"        // Input validation")
    content.append(f"        if (startIdx < 0 || endIdx < 0 || startIdx > endIdx) {{")
    content.append(f"            throw new IndexOutOfBoundsException(\"Invalid startIdx or endIdx. startIdx=\" + startIdx + \", endIdx=\" + endIdx);")
    content.append(f"        }}")
    # Add checks for input array lengths
    for p in required_params:
        if '[]' in p['type']: # Only check array types
            content.append(f"        if ({p['name']} == null || {p['name']}.length <= endIdx) {{")
            content.append(f"            throw new IndexOutOfBoundsException(\"Input array '{p['name']}' is null or too small for endIdx=\" + endIdx);")
            content.append(f"        }}")
    content.append(f"")
    content.append(f"        IntByReference outBegIdx = new IntByReference();")
    content.append(f"        IntByReference outNBElement = new IntByReference();")

    # Allocate output arrays
    if required_params:
        input_len_provider = required_params[0]['name']
        content.append(f"        int allocationSize = {input_len_provider}.length;")
    else:
        content.append(f"        int allocationSize = endIdx - startIdx + 1;")

    for p in output_params:
        content.append(f"        {p['type']} {p['name']} = new {p['type'].replace('[]','')}[allocationSize];")

    # Build the call to the native function
    call_params = ["startIdx", "endIdx"]
    call_params.extend([p['name'] for p in required_params])
    call_params.extend([p['name'] for p in optional_params])
    call_params.extend(["outBegIdx", "outNBElement"])
    call_params.extend([p['name'] for p in output_params])

    content.append(f"        int retCode = taLib.TA_{abbr}({', '.join(call_params)});")
    content.append(f"        if (retCode != 0) {{")
    content.append(f"            logger.error(\"TA-Lib function {abbr} returned error code: {{}}\", retCode);")
    content.append(f"            throw new ArithmeticException(\"TA-Lib function {abbr} returned error code: \" + retCode);")
    content.append(f"        }}")

    # Assign results
    content.append(f"        Result result = {result_class}.builder()")
    for p in output_params:
        content.append(f"            .{p['name']}({p['name']})")
    content.append(f"            .outBegIdx(outBegIdx.getValue())")
    content.append(f"            .outNBElement(outNBElement.getValue())")
    content.append(f"            .build();")
    content.append(f"        return result;")
    content.append(f"    }}")
    content.append("}")

    with open(os.path.join(main_java_dir, f"{camel_case_name}.java"), 'w') as f:
        f.write('\n'.join(content))

def create_test_class(test_java_dir, func):
    """
    Creates a JUnit test class for a single TA-Lib function.
    """
    camel_case_name = func.find('CamelCaseName').text

    required_params = []
    req_inputs = func.find('RequiredInputArguments')
    if req_inputs is not None:
        for arg in req_inputs.findall('RequiredInputArgument'):
            p_type = get_java_type(arg.find('Type').text)
            p_name = arg.find('Name').text.lower()
            required_params.append({'type': p_type, 'name': p_name})

    optional_params = []
    opt_inputs = func.find('OptionalInputArguments')
    if opt_inputs is not None:
        for arg in opt_inputs.findall('OptionalInputArgument'):
            p_type = get_java_type(arg.find('Type').text)
            p_name = "optIn" + arg.find('Name').text.replace(' ', '')
            default_val = arg.find('DefaultValue').text
            optional_params.append({'type': p_type, 'name': p_name, 'default': default_val})

    content = [
        f"package com.tictactec.ta.lib;",
        "",
        "import org.junit.jupiter.api.Test;",
        "import static org.junit.jupiter.api.Assertions.*;",
        "import com.tictactec.ta.lib.results.*;",
        "",
        f"public class {camel_case_name}Test {{",
        "",
        f"    @Test",
        f"    public void testExecute() {{",
        "        // This is a simple smoke test to ensure the function can be called without crashing.",
        "        int size = 100;",
        "        int startIdx = 0;",
        "        int endIdx = size - 1;"
    ]

    # Create dummy input data
    for p in required_params:
        content.append(f"        {p['type']} {p['name']} = new {p['type'].replace('[]','')}[size];")
        content.append(f"        for(int i=0; i<size; i++) {{ {p['name']}[i] = i; }} // Dummy data")

    call_params = ["startIdx", "endIdx"]
    call_params.extend([p['name'] for p in required_params])

    # Use default values for optional params
    for p in optional_params:
        call_params.append(f"({p['type']}){p['default']}")

    content.append(f"        Result result = {camel_case_name}.execute({', '.join(call_params)});")
    content.append(f"        assertNotNull(result);")
    content.append(f"        // Further assertions can be added here if expected values are known.")
    content.append(f"    }}")
    content.append(f"}}")

    with open(os.path.join(test_java_dir, f"{camel_case_name}Test.java"), 'w') as f:
        f.write('\n'.join(content))


if __name__ == "__main__":
    xml_file = './ta_func_api.xml'
    if not os.path.exists(xml_file):
        print(f"Error: '{xml_file}' not found. Make sure you are in the right directory.")
    else:
        create_maven_project(xml_file)
