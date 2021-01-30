package de.unikassel.soc.product.mappers;

import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto dto);
}
