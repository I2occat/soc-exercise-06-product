package de.unikassel.soc.product.controller;

import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.mappers.ProductMapper;
import de.unikassel.soc.product.mappers.ProductMapperImpl;
import de.unikassel.soc.product.model.ProductDto;
import de.unikassel.soc.product.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/product", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    ProductMapper mapper = new ProductMapperImpl();

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") UUID productId){

        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping({"/byCustomer/{customerId}"})
    public List<Product> getProducts(@PathVariable("customerId") String customerId){
        return productService.getProductsByCustomerId(customerId);
    }

    @GetMapping({"/products"})
    public Iterable<Product> allCustomers() {
        return productService.getAll();
    }

    @PostMapping // POST - create new product
    public ResponseEntity handlePost(@Valid @RequestBody ProductDto productDto){

        ProductDto savedDto = productService.saveNewProduct(productDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/product/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PostMapping({"/createrandom"})
    public ResponseEntity createRandom(){
        ProductDto saved = new ProductDto();
        saved.setProductName("Test");
        saved.setDescription("Test");
        saved.setId(UUID.randomUUID());
        saved.setCustomerId(UUID.randomUUID().toString());
        saved.setPrice(1.0);
        saved.setCurrency("€");
        productService.saveNewProduct(saved);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/product/" + saved.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping({"/createrandom/{customerId}"})
    public ResponseEntity createRandomForCustomer(@PathVariable("customerId") String customerId) {
        ProductDto saved = new ProductDto();
        saved.setProductName("Test");
        saved.setDescription("Test");
        saved.setId(UUID.randomUUID());
        saved.setCustomerId(customerId);
        saved.setPrice(1.0);
        saved.setCurrency("€");
        productService.saveNewProduct(saved);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/product/" + saved.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{productId}"})
    public ResponseEntity handleUpdate(@PathVariable("productId") UUID productId, @Valid @RequestBody ProductDto productDto){

        productService.updateProduct(productId, productDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") UUID productId){
        productService.deleteById(productId);
    }

}
