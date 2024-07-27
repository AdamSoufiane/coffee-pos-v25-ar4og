package ai.shreds.domain;

import ai.shreds.shared.SharedCreateOrderRequestParams;
import ai.shreds.shared.SharedOrderItemRequestParams;
import ai.shreds.shared.SharedOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
 * Service class for handling the business logic related to order creation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DomainOrderService {

    private final DomainOrderRepositoryPort orderRepository;
    private final DomainUserValidationPort userValidator;
    private final DomainInventoryCheckPort inventoryChecker;
    private final DomainOrderMapper orderMapper;
    private final DomainKafkaProducerService kafkaProducerService;

    /**
     * Creates a new order by validating the user, checking item availability, and saving the order to the database.
     * An OrderPlaced event is also published to Kafka.
     *
     * @param request The request parameters for creating the order.
     * @return The created DomainOrderEntity.
     */
    @Transactional
    public DomainOrderEntity createOrder(SharedCreateOrderRequestParams request) {
        try {
            // Step 1: Validate the user
            if (!userValidator.validateUser(request.getUserId())) {
                throw new IllegalArgumentException("Invalid user ID");
            }

            // Step 2: Check item availability and calculate total amount
            BigDecimal totalAmount = request.getItems().stream()
                    .map(item -> {
                        if (!inventoryChecker.checkItemAvailability(item.getItemId(), item.getQuantity())) {
                            throw new IllegalArgumentException("Item not available: " + item.getItemId());
                        }
                        return item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Step 3: Create a new DomainOrderEntity using the mapper
            DomainOrderEntity order = orderMapper.toEntity(request);
            order.setOrderId(UUID.randomUUID());
            order.setStatus("CREATED");
            order.setTotalAmount(totalAmount);
            order.setCreatedAt(Timestamp.from(Instant.now()));

            // Step 4: Save the order and its items to the database
            orderRepository.save(order);

            // Step 5: Publish an OrderPlaced event to Kafka
            kafkaProducerService.sendOrderPlacedEvent(order);

            // Step 6: Return the created order
            return order;
        } catch (Exception e) {
            log.error("Error creating order", e);
            throw e;
        }
    }
}