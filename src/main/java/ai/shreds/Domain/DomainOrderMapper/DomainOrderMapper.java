package ai.shreds.domain;

import ai.shreds.shared.SharedCreateOrderRequestParams;
import ai.shreds.shared.SharedOrderItemRequestParams;
import ai.shreds.shared.SharedOrderResponseDTO;
import ai.shreds.shared.SharedOrderItemResponseDTO;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.sql.Timestamp;

@Component
public class DomainOrderMapper {

    private DomainOrderMapper() {}

    public DomainOrderEntity toEntity(SharedCreateOrderRequestParams request) {
        if (request == null || request.getItems() == null) {
            throw new IllegalArgumentException("Request or items cannot be null");
        }
        DomainOrderEntity orderEntity = new DomainOrderEntity();
        orderEntity.setOrderId(UUID.randomUUID());
        orderEntity.setUserId(request.getUserId());
        orderEntity.setStatus("CREATED");
        orderEntity.setTotalAmount(calculateTotalAmount(request.getItems()));
        orderEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        orderEntity.setOrderItems(toOrderItemEntities(request.getItems(), orderEntity.getOrderId()));
        return orderEntity;
    }

    public SharedOrderResponseDTO toDTO(DomainOrderEntity order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        SharedOrderResponseDTO responseDTO = new SharedOrderResponseDTO();
        responseDTO.setOrderId(order.getOrderId());
        responseDTO.setStatus(order.getStatus());
        responseDTO.setMessage("Order has been created successfully.");
        return responseDTO;
    }

    private BigDecimal calculateTotalAmount(List<SharedOrderItemRequestParams> items) {
        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<DomainOrderItemEntity> toOrderItemEntities(List<SharedOrderItemRequestParams> items, UUID orderId) {
        return items.stream().map(item -> {
            DomainOrderItemEntity orderItemEntity = new DomainOrderItemEntity();
            orderItemEntity.setOrderItemId(UUID.randomUUID());
            orderItemEntity.setOrderId(orderId);
            orderItemEntity.setItemId(item.getItemId());
            orderItemEntity.setName(item.getName());
            orderItemEntity.setQuantity(item.getQuantity());
            orderItemEntity.setPrice(item.getPrice());
            return orderItemEntity;
        }).collect(Collectors.toList());
    }

    public SharedOrderItemResponseDTO toOrderItemDTO(DomainOrderItemEntity orderItemEntity) {
        SharedOrderItemResponseDTO orderItemDTO = new SharedOrderItemResponseDTO();
        orderItemDTO.setOrderItemId(orderItemEntity.getOrderItemId());
        orderItemDTO.setOrderId(orderItemEntity.getOrderId());
        orderItemDTO.setItemId(orderItemEntity.getItemId());
        orderItemDTO.setName(orderItemEntity.getName());
        orderItemDTO.setQuantity(orderItemEntity.getQuantity());
        orderItemDTO.setPrice(orderItemEntity.getPrice());
        return orderItemDTO;
    }
}