package tech.wvs.commentsms.api.client;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.time.Duration;

@Component
public class RestClientFactory {

    private final RestClient.Builder builder;

    public RestClientFactory(RestClient.Builder builder) {
        this.builder = builder;
    }

    public RestClient moderationServiceClient() {
        return builder
                .requestFactory(generateClientHttpRequestFactory())
                .baseUrl("http://localhost:8081")
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw new IOException("APIs integration error");
                })
                .build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setReadTimeout(Duration.ofSeconds(5));
        factory.setConnectTimeout(Duration.ofSeconds(3));

        return factory;
    }
}
