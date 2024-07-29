package ai.shreds.infrastructure;

import ai.shreds.domain.DomainUserValidationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Service
public class InfrastructureUserServiceClient implements DomainUserValidationPort {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public InfrastructureUserServiceClient(RestTemplate restTemplate, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    @PostConstruct
    private void validateConfiguration() {
        if (userServiceUrl == null || userServiceUrl.isEmpty()) {
            throw new IllegalArgumentException("User Service URL cannot be null or empty");
        }
    }

    @Override
    public boolean validateUser(UUID userId) {
        try {
            String url = String.format("%s/users/%s", userServiceUrl, userId.toString());
            restTemplate.getForObject(url, Void.class);
            log.info("User validation successful for user with ID: {}", userId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User not found: {}", userId);
            return false;
        } catch (Exception e) {
            log.error("Error validating user: {}", userId, e);
            return false;
        }
    }
}