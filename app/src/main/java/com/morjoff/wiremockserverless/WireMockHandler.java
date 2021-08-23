package com.morjoff.wiremockserverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.security.TokenAuthenticator;

public class WireMockHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final RoutingRequestHandler handler;

    public WireMockHandler() {
        Options options = WireMockConfiguration.options().adminAuthenticator(new TokenAuthenticator("todo"));
        ServerlessWireMockApp app = new ServerlessWireMockApp(options);
        this.handler = new RoutingRequestHandler(app.buildAdminRequestHandler(), app.buildStubRequestHandler());
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayRequest, Context context) {
        GatewayToWiremockRequestAdapter request = new GatewayToWiremockRequestAdapter(apiGatewayRequest);
        WiremockToGatewayResponseAdapter responder = new WiremockToGatewayResponseAdapter();

        this.handler.handle(request, responder);
        return responder.value();
    }
}
