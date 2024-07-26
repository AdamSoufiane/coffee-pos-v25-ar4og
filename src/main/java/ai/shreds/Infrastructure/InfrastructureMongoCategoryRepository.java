package ai.shreds.Infrastructure;

import ai.shreds.Domain.DomainCategoryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfrastructureMongoCategoryRepository extends MongoRepository<DomainCategoryEntity, ObjectId> {
    // Define methods for MongoDB operations if needed
}