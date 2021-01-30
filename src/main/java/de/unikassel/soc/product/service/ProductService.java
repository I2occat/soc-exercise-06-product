package de.unikassel.soc.product.service;



import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.model.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto getProductById(UUID productId);
    List<Product> getProductsByCustomerId(String customerId);

    ProductDto saveNewProduct(ProductDto productDto);

    void updateProduct(UUID productId, ProductDto productDto);

    void deleteById(UUID productId);
    Iterable<Product> getAll();
}
