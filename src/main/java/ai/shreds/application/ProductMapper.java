package ai.shreds.application;

import ai.shreds.domain.*;
import ai.shreds.shared.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    SharedProductDetailsApplicationDTO toSharedProductDetailsApplicationDTO(DomainProductEntity productEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    SharedProductListApplicationDTO toSharedProductListApplicationDTO(DomainProductEntity productEntity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    DomainCreateProductDTO toDomainCreateProductDTO(SharedCreateProductApplicationDTO createProductDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    DomainUpdateProductDTO toDomainUpdateProductDTO(SharedUpdateProductApplicationDTO updateProductDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    DomainProductEntity toDomainProductEntity(DomainCreateProductDTO createProductDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    DomainProductEntity toDomainProductEntity(DomainUpdateProductDTO updateProductDTO);
}