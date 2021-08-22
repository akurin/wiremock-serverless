package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.tomakehurst.wiremock.http.HttpResponder;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

public class WiremockToGatewayResponseAdapter implements HttpResponder {
    private APIGatewayProxyResponseEvent gatewayResponse;

    public APIGatewayProxyResponseEvent value() {
        if (gatewayResponse == null) {
            throw new IllegalStateException("The respond method must be called first.");
        }

        return gatewayResponse;
    }

    @Override
    public void respond(Request request, Response response) {
        gatewayResponse = new APIGatewayProxyResponseEvent()
                .withStatusCode(response.getStatus())
                .withBody(response.getBodyAsString());
    }
}
