package com.morjoff.wiremockserverless;

import com.github.tomakehurst.wiremock.http.*;
import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class FakeRequest implements Request {
    private String url;

    @Override
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    @Override
    public String getAbsoluteUrl() {
        return null;
    }

    @Override
    public RequestMethod getMethod() {
        return null;
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
        return null;
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
        return null;
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
        return null;
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
        return null;
    }
}
