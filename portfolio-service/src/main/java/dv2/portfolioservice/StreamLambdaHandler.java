package dv2.portfolioservice;


import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import dv2.portfolioservice.filter.CognitoIdentityFilter;
import spark.Spark;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumSet;

/**
 * AWS Lambda entry point
 */
public class StreamLambdaHandler implements RequestStreamHandler {

    private SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    private ServicesInitializer servicesInitializer;

    public StreamLambdaHandler() {
        try {
            handler = SparkLambdaContainerHandler.getAwsProxyHandler();
            servicesInitializer = new ServicesInitializer();
            initializeSparkContainer();
            initializeCognitoFilter();
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            throw new RuntimeException("Could not initialize Spark container", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }

    private void initializeSparkContainer() {
        servicesInitializer.getPortfolioController().defineResources();
        Spark.awaitInitialization();
    }

    private void initializeCognitoFilter() {
        // we use the onStartup method of the handler to register our custom filter
        handler.onStartup(servletContext -> {
            FilterRegistration.Dynamic registration = servletContext.addFilter("CognitoIdentityFilter", CognitoIdentityFilter.class);
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        });
    }
}
