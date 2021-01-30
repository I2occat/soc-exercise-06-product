package de.unikassel.soc.product.service;

import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.mappers.ProductMapper;
import de.unikassel.soc.product.mappers.ProductMapperImpl;
import de.unikassel.soc.product.model.ProductDto;
import de.unikassel.soc.product.repos.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo repo;
    private ProductMapper mapper = new ProductMapperImpl();

    @Override
    public ProductDto getProductById(UUID productId) {
        Product product = repo.findById(productId).get();
        return mapper.productToProductDto(product);
    }
    @Override
    public List<Product> getProductsByCustomerId(String customerId) {
        return repo.findProductsByCustomerId(customerId);
    }

    @Override
    public ProductDto saveNewProduct(ProductDto productDto) {
        Product product = mapper.productDtoToProduct(productDto);
        repo.save(product);
        return productDto;
    }

    @Override
    public void updateProduct(UUID productId, ProductDto productDto) {
        Product product = repo.findById(productId).get();
        product.setCustomerId(productDto.getCustomerId());
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCurrency(productDto.getCurrency());
    }

    @Override
    public void deleteById(UUID productId) {
        repo.deleteById(productId);
    }

    public ProductDto getProductByPrice(Double from, Double to) {
        List<Product> products = repo.findProductByPriceBetween(from, to);
        if(products.size()>0) {
            return mapper.productToProductDto(products.get(0));
        }
        return null;
    }
    @Override
    public Iterable<Product> getAll() {
        return repo.findAll();
    }
}
