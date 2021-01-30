package de.unikassel.soc.product.mappers;

import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.model.ProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-28T13:51:01+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.9.1 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setProductName( product.getProductName() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );
        productDto.setCurrency( product.getCurrency() );

        return productDto;
    }

    @Override
    public Product productDtoToProduct(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setProductName( dto.getProductName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setCurrency( dto.getCurrency() );

        return product;
    }
}
