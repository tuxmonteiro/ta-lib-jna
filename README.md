
# TA-Lib JNA Wrapper

This project provides a Java Native Access (JNA) wrapper for the TA-Lib (Technical Analysis Library) native library. It allows Java applications to utilize the high-performance financial functions provided by TA-Lib.

## Project Structure

The project is a standard Maven project.

- `pom.xml`: Maven project configuration, including JNA and JUnit 5 dependencies.
- `src/main/java/com/tictactec/ta/lib/TALib.java`: The core JNA interface that maps to the native `ta-lib` functions.
- `src/main/java/com/tictactec/ta/lib/results/*Result.java`: Result classes.
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

Return to parent project directory and build the project using Maven:

```bash
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

import com.tictactec.ta.lib.results.*;

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
            System.out.println("Output begins at index: " + smaResult.outBegIdx());
            System.out.println("Number of elements in output: " + smaResult.outNBElement());

            for (int i = 0; i < smaResult.outNBElement(); i++) {
                System.out.println("SMA[" + (smaResult.outBegIdx + i) + "] = " + ((RealResult)smaResult).outReal()[i]);
            }
        } catch (ArithmeticException e) {
            System.err.println("Error during TA-Lib function execution: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Input data error: " + e.getMessage());
        }
    }
}
```
