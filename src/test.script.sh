#!/bin/bash

javac Cryptographer.java

java Cryptographer

if diff -q output.txt expected_output.txt > /dev/null; then
    echo "Test Passed"
else
    echo "Test Failed"
    diff output.txt expected_output.txt
fi
