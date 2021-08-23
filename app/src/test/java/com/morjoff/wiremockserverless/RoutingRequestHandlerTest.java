package com.morjoff.wiremockserverless;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutingRequestHandlerTest {
    private SpyingRequestHandler adminRequestHandler;
    private SpyingRequestHandler stubRequestHandler;
    private RoutingRequestHandler sut;

    @BeforeEach
    public void BeforeEach() {
        this.adminRequestHandler = new SpyingRequestHandler();
        this.stubRequestHandler = new SpyingRequestHandler();
        this.sut = new RoutingRequestHandler(this.adminRequestHandler, this.stubRequestHandler);
    }

    @Test
    public void should_route_admin_requests() {

        FakeRequest request = new FakeRequest();
        request.setUrl("/__admin/mappings/new");
        DummyHttpResponder httpResponder = new DummyHttpResponder();

        this.sut.handle(request, httpResponder);

        assertTrue(adminRequestHandler.wasCalled());
        assertFalse(stubRequestHandler.wasCalled());
    }

    @Test
    public void should_route_stub_requests() {
        FakeRequest request = new FakeRequest();
        request.setUrl("/some");
        DummyHttpResponder httpResponder = new DummyHttpResponder();

        this.sut.handle(request, httpResponder);

        assertFalse(adminRequestHandler.wasCalled());
        assertTrue(stubRequestHandler.wasCalled());
    }
}
