package com.morjoff.wiremockserverless;

import com.github.tomakehurst.wiremock.http.HttpResponder;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestHandler;

public class SpyingRequestHandler implements RequestHandler {
    private boolean wasCalled;

    @Override
    public void handle(Request request, HttpResponder httpResponder) {
        this.wasCalled = true;
    }

    public boolean wasCalled() {
        return this.wasCalled;
    }
}
