package com.morjoff.wiremockserverless;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.apigateway.*;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.lambda.*;

import java.util.HashMap;
import java.util.Map;

public class WiremockServerlessStack extends Stack {
    public WiremockServerlessStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public WiremockServerlessStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Map<String, String> lambdaEnvMap = new HashMap<>();

        Function lambdaFunction = new Function(this, "wiremock", FunctionProps
                .builder()
                .code(Code.fromAsset("../app/target/wiremock-serverless-1.0-SNAPSHOT-jar-with-dependencies.jar"))
                .handler("com.morjoff.wiremockserverless.WireMockHandler")
                .runtime(Runtime.JAVA_11)
                .environment(lambdaEnvMap)
                .timeout(Duration.seconds(30))
                .memorySize(512)
                .tracing(Tracing.ACTIVE)
                .build());

        RestApi api = new RestApi(this, "wiremockApi", RestApiProps
                .builder()
                .restApiName("Wiremock")
                .deployOptions(StageOptions.builder()
                        .tracingEnabled(true)
                        .dataTraceEnabled(true)
                        .loggingLevel(MethodLoggingLevel.INFO)
                        .build())
                .deploy(true)
                .cloudWatchRole(false)
                .build());

        Integration lambdaIntegration = new LambdaIntegration(lambdaFunction, LambdaIntegrationOptions.builder().build());

        IResource root = api.getRoot();
        root.addResource("{proxy+}").addMethod("ANY", lambdaIntegration);
        root.addMethod("ANY", lambdaIntegration);
    }
}
