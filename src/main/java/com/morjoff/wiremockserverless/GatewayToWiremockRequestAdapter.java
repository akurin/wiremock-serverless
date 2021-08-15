package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.github.tomakehurst.wiremock.http.*;
import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GatewayToWiremockRequestAdapter implements Request {
    private final APIGatewayProxyRequestEvent requestEvent;

    public GatewayToWiremockRequestAdapter(APIGatewayProxyRequestEvent requestEvent) {
        this.requestEvent = requestEvent;
    }

    @Override
    public String getUrl() {
        return "todo";
    }

    @Override
    public String getAbsoluteUrl() {
        return "http://todo";
    }

    @Override
    public RequestMethod getMethod() {
        return new RequestMethod(this.requestEvent.getHttpMethod());
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getClientIp() {
        return null;
    }

    @Override
    public String getHeader(String key) {
        return null;
    }

    @Override
    public HttpHeader header(String key) {
        return null;
    }

    @Override
    public ContentTypeHeader contentTypeHeader() {
        return null;
    }

    @Override
    public HttpHeaders getHeaders() {
        Map<String, String> headers = this.requestEvent.getHeaders();
        if (headers == null) {
            return new HttpHeaders();
        }

        Iterable<HttpHeader> wiremockHeaders = headers
                .entrySet()
                .stream()
                .map(entry -> new HttpHeader(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new HttpHeaders(wiremockHeaders);
    }

    @Override
    public boolean containsHeader(String key) {
        return false;
    }

    @Override
    public Set<String> getAllHeaderKeys() {
        return null;
    }

    @Override
    public Map<String, Cookie> getCookies() {
        return Collections.emptyMap();
    }

    @Override
    public QueryParameter queryParameter(String key) {
        return null;
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }

    @Override
    public String getBodyAsString() {
        return "";
    }

    @Override
    public String getBodyAsBase64() {
        return null;
    }

    @Override
    public boolean isMultipart() {
        return false;
    }

    @Override
    public Collection<Part> getParts() {
        return null;
    }

    @Override
    public Part getPart(String name) {
        return null;
    }

    @Override
    public boolean isBrowserProxyRequest() {
        return false;
    }

    @Override
    public Optional<Request> getOriginalRequest() {
        return Optional.absent();
    }
}
