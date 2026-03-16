package com.lldproject.productcatalogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lldproject.productcatalogservice.dtos.ProductDto;
import com.lldproject.productcatalogservice.models.Product;
import com.lldproject.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void TestGetAllProducts_RunSuccessfully() throws Exception {
        //Arrange
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("Macbook Air");
        product1.setPrice(100000D);

        Product product2 = new Product();
        product2.setId(5L);
        product2.setName("Macbook Pro");
        product2.setPrice(150000D);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts())
                .thenReturn(productList);

        ProductDto productDto1 = new ProductDto();
        productDto1.setId(2L);
        productDto1.setName("Macbook Air");
        productDto1.setPrice(100000D);

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(5L);
        productDto2.setName("Macbook Pro");
        productDto2.setPrice(150000D);

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);
        String response =
                objectMapper.writeValueAsString(productDtos);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andExpect(jsonPath("$[0].name").value("Macbook Air"));
    }

    @Test
    public void TestCreateProduct_WithValidInput_RunSuccessfully() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setName("Ipad");
        productDto.setPrice(75000D);

        Product product = new Product();
        product.setId(10L);
        product.setName("Ipad");
        product.setPrice(75000D);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        String response = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .content(response)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andExpect(jsonPath("$.name").value("Ipad"));
    }
}

//{
//         "id" : 1,
//        "name" : "Ipad",
//        "price" : 75000
//}
//
//$
