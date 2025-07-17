package tech.wvs.commentsms.api.client.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tech.wvs.commentsms.api.client.ModerationServiceClient;
import tech.wvs.commentsms.api.client.RestClientFactory;
import tech.wvs.commentsms.api.web.ModerationInput;
import tech.wvs.commentsms.api.web.ModerationOutput;

@Component
public class ModerationServiceClientImpl implements ModerationServiceClient {


    private final RestClient restClient;

    public ModerationServiceClientImpl(RestClientFactory factory) {
        this.restClient = factory.moderationServiceClient();
    }

    @Override
    public ModerationOutput validadeComment(ModerationInput dto) {
        return restClient.post()
                .uri("/api/moderate")
                .body(dto)
                .retrieve()
                .body(ModerationOutput.class);
    }
}
