import os
import xml.etree.ElementTree as ET

def create_maven_project(xml_file):
    """
    Generates a complete Maven project for ta-lib based on the provided XML definition.
    """
    base_dir = 'ta-lib-java'
    if not os.path.exists(base_dir):
        os.makedirs(base_dir)

    # Create directory structure
    main_java_dir = os.path.join(base_dir, 'src/main/java/com/tictactec/ta/lib')
    test_java_dir = os.path.join(base_dir, 'src/test/java/com/tictactec/ta/lib')
    os.makedirs(main_java_dir, exist_ok=True)
    os.makedirs(test_java_dir, exist_ok=True)

    # Parse the XML file
    tree = ET.parse(xml_file)
    root = tree.getroot()
    functions = list(root.findall('FinancialFunction'))

    # Create pom.xml
    create_pom_xml(base_dir)

    # Create TALib.java core interface
    create_core_lib_class(main_java_dir, functions)

    # Create a class for each function and its test
    for func in functions:
        create_function_class(main_java_dir, func)
        create_test_class(test_java_dir, func)

    # Create README.md
    create_project_readme(base_dir)

    print(f"Maven project created in '{base_dir}' directory.")

def create_project_readme(base_dir):
    """
    Creates the README.md file for the generated Maven project.
    """
    readme_content = """
# TA-Lib JNA Wrapper

This project provides a Java Native Access (JNA) wrapper for the TA-Lib (Technical Analysis Library) native library. It allows Java applications to utilize the high-performance financial functions provided by TA-Lib.

## Project Structure

The project is a standard Maven project.

- `pom.xml`: Maven project configuration, including JNA and JUnit 5 dependencies.
- `src/main/java/com/tictactec/ta/lib/TALib.java`: The core JNA interface that maps to the native `ta-lib` functions.
- `src/main/java/com/tictactec/ta/lib/{FunctionName}.java`: Wrapper classes for each TA-Lib function, providing a more Java-friendly API.
- `src/test/java/com/tictactec/ta/lib/{FunctionName}Test.java`: Unit tests for each function to validate the integration with the native library.

## Prerequisites

Before building and running this project, you need to have the native TA-Lib library installed on your system.

### Building and Installing Native TA-Lib

1.  **Clone the TA-Lib repository:**
    If you haven't already, clone the official TA-Lib repository:
    ```bash
    git clone https://github.com/TA-Lib/ta-lib.git
    cd ta-lib
    ```
    *(Assuming this project is generated within the cloned `ta-lib` directory)*

2.  **Build using CMake:**
    ```bash
    mkdir build
    cd build
    cmake ..
    make
    sudo make install # Installs libta-lib.so to /usr/local/lib
    ```
    Alternatively, if you don't want to install system-wide, you can skip `sudo make install` and instead set `LD_LIBRARY_PATH` as described below.

### Setting `LD_LIBRARY_PATH` (if not installed system-wide)

If you did not run `sudo make install`, you need to tell Java where to find the native library. You can do this by setting the `LD_LIBRARY_PATH` environment variable to the directory where `libta-lib.so` was built (e.g., the `build` directory within the TA-Lib source).

For example, if your TA-Lib source is in `/home/tuxmonteiro/dev/github.com/tuxmonteiro/ta-lib`:
```bash
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/tuxmonteiro/dev/github.com/tuxmonteiro/ta-lib/build
```
You would need to run this command in your terminal session *before* running any Java applications or tests that use this JNA wrapper.

## Building the Java Project

Navigate to the `ta-lib-java` directory and build the project using Maven:

```bash
cd /home/tuxmonteiro/dev/github.com/tuxmonteiro/ta-lib/ta-lib-java
mvn clean install
```

This command will:
- Compile the Java source code.
- Run the unit tests (which will attempt to load and call the native TA-Lib functions).
- Package the project into a JAR file.

## Usage Example

Here's a simple example of how to use one of the generated TA-Lib functions (e.g., `SMA` - Simple Moving Average):

```java
package com.tictactec.ta.lib;

import com.tictactec.ta.lib.Sma.Result;

public class Example {
    public static void main(String[] args) {
        // Sample input data
        double[] inReal = new double[100];
        for (int i = 0; i < 100; i++) {
            inReal[i] = i * 1.0; // Dummy data
        }

        int startIdx = 0;
        int endIdx = inReal.length - 1;
        int optInTimePeriod = 10; // Example: 10-period SMA

        try {
            // Call the SMA function
            Result smaResult = Sma.execute(startIdx, endIdx, inReal, optInTimePeriod);

            System.out.println("SMA Calculation Result:");
            System.out.println("Output begins at index: " + smaResult.outBegIdx);
            System.out.println("Number of elements in output: " + smaResult.outNBElement);

            for (int i = 0; i < smaResult.outNBElement; i++) {
                System.out.println("SMA[" + (smaResult.outBegIdx + i) + "] = " + smaResult.outReal[i]);
            }
        } catch (ArithmeticException e) {
            System.err.println("Error during TA-Lib function execution: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Input data error: " + e.getMessage());
        }
    }
}
```
"""
    with open(os.path.join(base_dir, 'README.md'), 'w') as f:
        f.write(readme_content)

def create_pom_xml(base_dir):
    """
    Creates the pom.xml file for the Maven project.
    """
    pom_content = """
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.tictactec.ta.lib</groupId>
    <artifactId>ta-lib-jna</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>TA-Lib JNA Wrapper</name>
    <description>A JNA-based wrapper for the TA-Lib library.</description>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>net.java.dev.jna</groupId>
            <artifactId>jna</artifactId>
            <version>5.12.1</version>
        </dependency>
        <!-- SLF4J API -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.32</version>
        </dependency>
        <!-- Logback Classic (SLF4J implementation) -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
            </plugin>
        </plugins>
    </build>
</project>
"""
    with open(os.path.join(base_dir, 'pom.xml'), 'w') as f:
        f.write(pom_content)

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
    if outputs is not None:
        for arg in outputs.findall('OutputArgument'):
            p_type = get_java_type(arg.find('Type').text)
            p_name = arg.find('Name').text
            output_params.append({'type': p_type, 'name': p_name})

    # Start building the class content
    content = [
        f"package com.tictactec.ta.lib;",
        "",
        "import com.sun.jna.ptr.IntByReference;",
        "import org.slf4j.Logger;",
        "import org.slf4j.LoggerFactory;",
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

    # Result class
    content.append(f"    public static class Result {{")
    for p in output_params:
        content.append(f"        public {p['type']} {p['name']};")
    content.append(f"        public int outBegIdx;")
    content.append(f"        public int outNBElement;")
    content.append(f"    }}")
    content.append("")

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
    content.append(f"        Result result = new Result();")
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
    for p in output_params:
        content.append(f"        result.{p['name']} = {p['name']};")
    content.append(f"        result.outBegIdx = outBegIdx.getValue();")
    content.append(f"        result.outNBElement = outNBElement.getValue();")

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

    content.append(f"        {camel_case_name}.Result result = {camel_case_name}.execute({', '.join(call_params)});")
    content.append(f"        assertNotNull(result);")
    content.append(f"        // Further assertions can be added here if expected values are known.")
    content.append(f"    }}")
    content.append(f"}}")

    with open(os.path.join(test_java_dir, f"{camel_case_name}Test.java"), 'w') as f:
        f.write('\n'.join(content))


if __name__ == "__main__":
    xml_file = 'ta_func_api.xml'
    if not os.path.exists(xml_file):
        print(f"Error: '{xml_file}' not found. Make sure you are in the right directory.")
    else:
        create_maven_project(xml_file)
