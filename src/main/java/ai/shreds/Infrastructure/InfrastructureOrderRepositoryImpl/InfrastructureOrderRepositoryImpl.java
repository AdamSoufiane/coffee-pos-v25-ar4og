package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainOrderEntity; 
 import ai.shreds.domain.DomainOrderRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.data.jpa.repository.JpaRepository; 
 import lombok.RequiredArgsConstructor; 
  
 import java.util.UUID; 
 import java.util.Optional; 
  
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
         Optional<DomainOrderEntity> order = orderJpaRepository.findById(orderId); 
         if (order.isPresent()) { 
             return order.get(); 
         } else { 
             throw new OrderNotFoundException("Order with id " + orderId + " not found"); 
         } 
     } 
  
     interface OrderJpaRepository extends JpaRepository<DomainOrderEntity, UUID> {} 
 } 
  
 class OrderNotFoundException extends RuntimeException { 
     public OrderNotFoundException(String message) { 
         super(message); 
     } 
 }