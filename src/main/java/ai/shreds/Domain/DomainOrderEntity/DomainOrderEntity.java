package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.persistence.*; 
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.List; 
 import java.util.UUID; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "orders") 
 public class DomainOrderEntity { 
     @Id 
     @GeneratedValue 
     private UUID orderId; 
  
     @Column(nullable = false) 
     private UUID userId; 
  
     @Column(nullable = false, length = 50) 
     private String status; 
  
     @Column(nullable = false, precision = 10, scale = 2) 
     private BigDecimal totalAmount; 
  
     @Column(nullable = false) 
     private Timestamp createdAt; 
  
     @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true) 
     private List<DomainOrderItemEntity> orderItems; 
 } 
 