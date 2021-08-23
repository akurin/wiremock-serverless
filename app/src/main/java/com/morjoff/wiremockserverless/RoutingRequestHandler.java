package com.morjoff.wiremockserverless;

import com.github.tomakehurst.wiremock.http.HttpResponder;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestHandler;

import java.util.Arrays;
import java.util.Optional;

public class RoutingRequestHandler implements RequestHandler {
    private final RequestHandler adminRequestHandler;
    private final RequestHandler stubRequestHandler;

    public RoutingRequestHandler(RequestHandler adminRequestHandler, RequestHandler stubRequestHandler) {
        this.adminRequestHandler = adminRequestHandler;
        this.stubRequestHandler = stubRequestHandler;
    }

    @Override
    public void handle(Request request, HttpResponder httpResponder) {
        String url = request.getUrl();
        Optional<String> firstSegment = Arrays.stream(url.split("/"))
                .filter(segment -> !segment.isEmpty())
                .findFirst();

        if (firstSegment.equals(Optional.of("__admin"))) {
            adminRequestHandler.handle(request, httpResponder);
        } else {
            stubRequestHandler.handle(request, httpResponder);
        }
    }
}
