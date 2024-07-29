package ai.shreds.domain;

import ai.shreds.domain.DomainOrderEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.CircuitBreaker;
import java.util.UUID;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.lang.Exception;

@Service
public class DomainKafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(DomainKafkaProducerService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.orderPlaced}")
    private String orderPlacedTopic;

    public DomainKafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    @CircuitBreaker(value = { Exception.class }, resetTimeout = 10000)
    public void sendOrderPlacedEvent(DomainOrderEntity order) {
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            kafkaTemplate.send(orderPlacedTopic, orderJson);
            logger.info("OrderPlaced event sent to Kafka for order id: {}", order.getOrderId());
        } catch (JsonProcessingException e) {
            logger.error("Error serializing order entity to JSON", e);
        } catch (Exception e) {
            logger.error("Error sending OrderPlaced event to Kafka", e);
            throw e;
        }
    }
}