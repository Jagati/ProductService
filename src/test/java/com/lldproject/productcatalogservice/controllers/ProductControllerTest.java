package com.lldproject.productcatalogservice.controllers;

import com.lldproject.productcatalogservice.dtos.ProductDto;
import com.lldproject.productcatalogservice.models.Product;
import com.lldproject.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    //@Autowired
    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidId_ReturnsProductSuccessfully() {
        //Arrange
        Long productId = 2L;

        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        product.setPrice(100000D);
        when(productService.getProductById(productId))
                .thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =
                productController.getProductById(productId);

        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(HttpStatus.OK,
                productDtoResponseEntity.getStatusCode());

        assertEquals(productId,productDtoResponseEntity.getBody().getId());
        assertEquals(100000D, productDtoResponseEntity.getBody().getPrice());
        assertEquals("Iphone", productDtoResponseEntity.getBody().getName());
        assertNull(productDtoResponseEntity.getBody().getCategory());
        assertNull(productDtoResponseEntity.getBody().getDescription());
        verify(productService,times(1)).getProductById(productId);
    }

    @Test
    public void TestGetProductById_WithNegativeId_ThrowsIllegalArgumentException() {
        //Arrange
        Long productId = -1L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> productController.getProductById(productId));

        assertEquals("Invalid Id passed",exception.getMessage());
        verify(productService,times(0)).getProductById(productId);
    }

}
