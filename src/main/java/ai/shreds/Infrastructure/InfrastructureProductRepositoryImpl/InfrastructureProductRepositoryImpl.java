package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    private final MongoRepository<DomainProductEntity, String> mongoRepository;

    @Autowired
    public InfrastructureProductRepositoryImpl(MongoRepository<DomainProductEntity, String> mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Optional<DomainProductEntity> findById(String id) {
        try {
            return mongoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error finding product by id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<DomainProductEntity> findAll() {
        try {
            return mongoRepository.findAll();
        } catch (Exception e) {
            log.error("Error finding all products", e);
            return List.of();
        }
    }

    @Override
    public DomainProductEntity save(DomainProductEntity product) {
        try {
            return mongoRepository.save(product);
        } catch (Exception e) {
            log.error("Error saving product: {}", product, e);
            return null;
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            mongoRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting product by id: {}", id, e);
        }
    }
}