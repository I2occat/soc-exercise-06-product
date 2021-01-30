package de.unikassel.soc.product;

import de.unikassel.soc.product.controller.ProductController;
import de.unikassel.soc.product.domain.Product;
import de.unikassel.soc.product.mappers.ProductMapper;
import de.unikassel.soc.product.mappers.ProductMapperImpl;
import de.unikassel.soc.product.model.ProductDto;
import de.unikassel.soc.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductApplicationTest {

    @Mock
    ProductService productService;

    ProductController productController;
    ProductMapper mapper = new ProductMapperImpl();

    static final String BASE_PATH = "/api/v1/product";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void getProductsByCustomerId() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        List<Product> productList= new ArrayList<>();
        productList.add(mapper.productDtoToProduct(new ProductDto(UUID.randomUUID(), "1", "Test1", "Test", 0.5, "Eur")));
        productList.add(mapper.productDtoToProduct(new ProductDto(UUID.randomUUID(), "1", "Test2", "Test", 2.5, "Eur")));
        when(productService.getProductsByCustomerId("1")).thenReturn(productList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH+"/byCustomer/1")
        ).andExpect(status().isOk()).andReturn();

        String resultStr  = result.getResponse().getContentAsString();
        assertTrue(resultStr.contains(productList.get(0).getId().toString()));
        assertTrue(resultStr.contains(productList.get(1).getId().toString()));


    }

}