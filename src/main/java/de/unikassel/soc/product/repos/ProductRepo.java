package de.unikassel.soc.product.repos;

import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.model.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    List<Product> findProductByPriceBetween(Double from, Double to);
    List<Product> findProductsByCustomerId(String customerId);
}
