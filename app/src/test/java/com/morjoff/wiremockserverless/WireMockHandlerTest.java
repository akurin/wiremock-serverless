package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WireMockHandlerTest {
    @Test
    public void should_handle_request() {
        WireMockHandler sut = new WireMockHandler();

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHttpMethod("POST");

        APIGatewayProxyResponseEvent response = sut.handleRequest(requestEvent, new DummyContext());

        assertEquals(response.getStatusCode(), 404);
        assertNull(response.getBody());
    }
}