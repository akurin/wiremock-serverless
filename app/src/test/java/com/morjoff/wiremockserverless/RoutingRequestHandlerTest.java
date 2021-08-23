package com.morjoff.wiremockserverless;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutingRequestHandlerTest {
    @Test
    public void should_route_admin_requests() {
        TestRequestHandler adminRequestHandler = new TestRequestHandler();
        TestRequestHandler stubRequestHandler = new TestRequestHandler();
        RoutingRequestHandler sut = new RoutingRequestHandler(adminRequestHandler, stubRequestHandler);
        TestRequest request = new TestRequest();
        request.setUrl("/__admin/mappings/new");
        TestHttpResponder httpResponder = new TestHttpResponder();

        sut.handle(request, httpResponder);

        assertTrue(adminRequestHandler.wasCalled());
        assertFalse(stubRequestHandler.wasCalled());
    }

    @Test
    public void should_route_stub_requests() {
        TestRequestHandler adminRequestHandler = new TestRequestHandler();
        TestRequestHandler stubRequestHandler = new TestRequestHandler();
        RoutingRequestHandler sut = new RoutingRequestHandler(adminRequestHandler, stubRequestHandler);
        TestRequest request = new TestRequest();
        request.setUrl("/some");
        TestHttpResponder httpResponder = new TestHttpResponder();

        sut.handle(request, httpResponder);

        assertFalse(adminRequestHandler.wasCalled());
        assertTrue(stubRequestHandler.wasCalled());
    }
}
