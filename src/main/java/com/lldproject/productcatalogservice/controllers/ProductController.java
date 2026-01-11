package com.lldproject.productcatalogservice.controllers;

import com.lldproject.productcatalogservice.dtos.CategoryDto;
import com.lldproject.productcatalogservice.dtos.ProductDto;
import com.lldproject.productcatalogservice.exceptions.ProductNotFoundException;
import com.lldproject.productcatalogservice.models.Category;
import com.lldproject.productcatalogservice.models.Product;
import com.lldproject.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        if(products!=null){
           for( Product product:products){
               ProductDto productDto = from(product);
               productDtos.add(productDto);
           }
           return new ResponseEntity<>(productDtos, HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        if(id<1){
            throw new IllegalArgumentException("ID should be greater than 0");
        }
        Product product = productService.getProductById(id);
        if(product==null){
            throw new ProductNotFoundException("Product not found");
        }
        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {

        return productDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        if(id<1){
            throw new IllegalArgumentException("ID should be greater than 0");
        }
        Product product = from(productDto);
        Product responseProduct = productService.updateProduct(product, id);
        if(responseProduct!=null){
            return new ResponseEntity<>(from(responseProduct), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        if(id<1){
            throw new IllegalArgumentException("ID should be greater than 0");
        }
        Product product = productService.deleteProduct(id);
        if(product!=null){
            return new ResponseEntity<>(from(product), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        if(productDto.getCategory()!=null){
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }
    private ProductDto from(Product  product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

}
