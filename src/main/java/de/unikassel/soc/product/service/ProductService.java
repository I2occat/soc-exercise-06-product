package de.unikassel.soc.product.service;



import de.unikassel.soc.product.model.ProductDto;

import java.util.UUID;

public interface ProductService {
    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductDto productDto);

    void updateProduct(UUID productId, ProductDto productDto);

    void deleteById(UUID productId);
}
