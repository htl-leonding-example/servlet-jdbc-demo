#!/bin/bash

RUNNER=$(ls jetty-servlet-demo.jar)

echo "starting jetty $RUNNER..."
java -jar $RUNNER
