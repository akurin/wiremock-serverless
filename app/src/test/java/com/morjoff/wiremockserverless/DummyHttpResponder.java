package com.morjoff.wiremockserverless;

import com.github.tomakehurst.wiremock.http.HttpResponder;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

public class DummyHttpResponder implements HttpResponder {
    @Override
    public void respond(Request request, Response response) {
    }
}
