package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class DummyLogger implements LambdaLogger {
    public DummyLogger() {
    }

    public void log(String message) {
    }

    public void log(byte[] message) {
    }
}