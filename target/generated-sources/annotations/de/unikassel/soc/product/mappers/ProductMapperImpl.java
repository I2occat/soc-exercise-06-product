package de.unikassel.soc.product.mappers;

import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.domain.Product.ProductBuilder;
import de.unikassel.soc.product.model.ProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-30T15:48:10+0100",
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
        productDto.setCustomerId( product.getCustomerId() );
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

        ProductBuilder product = Product.builder();

        product.id( dto.getId() );
        product.customerId( dto.getCustomerId() );
        product.productName( dto.getProductName() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );
        product.currency( dto.getCurrency() );

        return product.build();
    }
}
