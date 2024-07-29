package ai.shreds.infrastructure;

import ai.shreds.domain.DomainOrderEntity;
import ai.shreds.domain.DomainOrderRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InfrastructureOrderRepositoryImpl implements DomainOrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void save(DomainOrderEntity order) {
        orderJpaRepository.save(order);
    }

    @Override
    public DomainOrderEntity findById(UUID orderId) {
        return orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
    }

    interface OrderJpaRepository extends JpaRepository<DomainOrderEntity, UUID> {}

}

class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}