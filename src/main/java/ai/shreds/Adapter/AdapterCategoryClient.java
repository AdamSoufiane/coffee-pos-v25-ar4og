package ai.shreds.Adapter;

import ai.shreds.Domain.DomainCategoryServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Component
public class AdapterCategoryClient implements DomainCategoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryClient.class);
    private final WebClient webClient;

    public AdapterCategoryClient(WebClient.Builder webClientBuilder, @Value("${category.service.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public boolean checkCategoryExists(String id) {
        try {
            webClient.get()
                    .uri("/categories/{id}", id)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .timeout(Duration.ofSeconds(5))
                    .retry(3)
                    .block();
            logger.info("Category {} exists", id);
            return true;
        } catch (WebClientResponseException.NotFound e) {
            logger.warn("Category {} not found", id);
            return false;
        } catch (WebClientResponseException e) {
            logger.error("Error checking category existence for {}: {}", id, e.getMessage());
            throw new RuntimeException("Error checking category existence", e);
        } catch (Exception e) {
            logger.error("Unexpected error checking category existence for {}: {}", id, e.getMessage());
            throw new RuntimeException("Unexpected error checking category existence", e);
        }
    }
}