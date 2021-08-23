package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WireMockHandlerTest {
    @Test
    public void should_return_mappings() {
        WireMockHandler sut = new WireMockHandler();

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(new HashMap<>() {{
            put("Authorization", "Token todo");
        }});
        requestEvent.setHttpMethod("GET");
        requestEvent.setPath("/__admin/mappings");

        APIGatewayProxyResponseEvent response = sut.handleRequest(requestEvent, new DummyContext());

        assertEquals(response.getStatusCode(), 200);
        assertEquals("{\n" +
                "  \"mappings\" : [ ],\n" +
                "  \"meta\" : {\n" +
                "    \"total\" : 0\n" +
                "  }\n" +
                "}", response.getBody());
    }
}