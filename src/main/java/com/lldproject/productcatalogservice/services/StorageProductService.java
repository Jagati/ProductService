package com.lldproject.productcatalogservice.services;

import com.lldproject.productcatalogservice.exceptions.ProductAlreadyPresentException;
import com.lldproject.productcatalogservice.exceptions.ProductNotFoundException;
import com.lldproject.productcatalogservice.models.Product;
import com.lldproject.productcatalogservice.models.State;
import com.lldproject.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductService{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = productRepo.findById(product.getId());
        if(productOptional.isEmpty()){
            return productRepo.save(product);
        }
        else{
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()){
            product.setId(id);
            product.setUpdatedAt(new Date());
            return productRepo.save(product);
        }
        else {
            return null;
        }
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(product.getState().equals(State.ACTIVE)){
                product.setState(State.DELETED);
                productRepo.save(product);
            }
            else {
                productRepo.deleteById(id);
            }
            return productOptional.get();
        }
        return null;
    }
}
