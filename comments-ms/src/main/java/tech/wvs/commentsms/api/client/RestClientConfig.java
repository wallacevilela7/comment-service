package tech.wvs.commentsms.api.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    public ModerationServiceClient getClient(RestClientFactory factory) {
        RestClient restClient = factory.moderationServiceClient();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();

        return proxyFactory.createClient(ModerationServiceClient.class);
    }
}
