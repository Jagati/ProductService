package com.lldproject.productcatalogservice.services;

import com.lldproject.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Product updateProduct(Product product, Long id);
    public Product deleteProduct(Long id);
}
