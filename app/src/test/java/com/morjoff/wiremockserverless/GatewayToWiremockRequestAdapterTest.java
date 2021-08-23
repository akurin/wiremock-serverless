package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class GatewayToWiremockRequestAdapterTest {
    @Test
    public void should_return_headers() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(new HashMap<>() {{
            put("SomeHeader", "some-value");
        }});
        GatewayToWiremockRequestAdapter sut = new GatewayToWiremockRequestAdapter(requestEvent);

        assertEquals(new HttpHeader("SomeHeader", "some-value"), sut.header("SomeHeader"));
        assertEquals("some-value", sut.getHeader("SomeHeader"));
        assertTrue(sut.containsHeader("SomeHeader"));
    }

    @Test
    public void should_return_absent_headers() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(new HashMap<>());
        GatewayToWiremockRequestAdapter sut = new GatewayToWiremockRequestAdapter(requestEvent);

        assertEquals(HttpHeader.absent("MissingHeader"), sut.header("MissingHeader"));
        assertNull(sut.getHeader("MissingHeader"));
        assertFalse(sut.containsHeader("MissingHeader"));
    }

    @Test
    public void should_return_content_type_header() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(new HashMap<>() {{
            put("Content-Type", "application/json");
        }});
        GatewayToWiremockRequestAdapter sut = new GatewayToWiremockRequestAdapter(requestEvent);

        assertEquals(new ContentTypeHeader("application/json"), sut.contentTypeHeader());
    }

    @Test
    public void should_return_absent_content_type_header() {
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(new HashMap<>());
        GatewayToWiremockRequestAdapter sut = new GatewayToWiremockRequestAdapter(requestEvent);

        assertEquals(ContentTypeHeader.absent(), sut.contentTypeHeader());
    }
}
