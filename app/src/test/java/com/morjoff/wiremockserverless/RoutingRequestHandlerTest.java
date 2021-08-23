package com.morjoff.wiremockserverless;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutingRequestHandlerTest {
    private TestRequestHandler adminRequestHandler;
    private TestRequestHandler stubRequestHandler;
    private RoutingRequestHandler sut;

    @BeforeEach
    public void BeforeEach() {
        this.adminRequestHandler = new TestRequestHandler();
        this.stubRequestHandler = new TestRequestHandler();
        this.sut = new RoutingRequestHandler(this.adminRequestHandler, this.stubRequestHandler);
    }

    @Test
    public void should_route_admin_requests() {

        TestRequest request = new TestRequest();
        request.setUrl("/__admin/mappings/new");
        TestHttpResponder httpResponder = new TestHttpResponder();

        this.sut.handle(request, httpResponder);

        assertTrue(adminRequestHandler.wasCalled());
        assertFalse(stubRequestHandler.wasCalled());
    }

    @Test
    public void should_route_stub_requests() {
        TestRequest request = new TestRequest();
        request.setUrl("/some");
        TestHttpResponder httpResponder = new TestHttpResponder();

        this.sut.handle(request, httpResponder);

        assertFalse(adminRequestHandler.wasCalled());
        assertTrue(stubRequestHandler.wasCalled());
    }
}
