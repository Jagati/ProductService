package com.lldproject.productcatalogservice.services;

import com.lldproject.productcatalogservice.clients.FakeStoreApiClient;
import com.lldproject.productcatalogservice.dtos.FakeStoreProductDto;
import com.lldproject.productcatalogservice.models.Category;
import com.lldproject.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements IProductService{
    @Autowired
    private FakeStoreApiClient  fakeStoreApiClient;

    @Override
    public Product getProductById(Long id){
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(id);
        if(fakeStoreProductDto == null){
            return null;
        }
        return from(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();
        if(fakeStoreProductDtos!=null){
            for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
                Product product = from(fakeStoreProductDto);
                products.add(product);
            }
            return products;
        }
        return null;
    }
    @Override
    public Product createProduct(Product product){
        return null;
    }

    @Override
    public Product updateProduct(Product product, Long id){
        FakeStoreProductDto fakeStoreProductDto = from(product);
        FakeStoreProductDto fakeStoreProductDtoResponse = fakeStoreApiClient.updateProduct(fakeStoreProductDto, id);
        if(fakeStoreProductDtoResponse != null){
            return from(fakeStoreProductDtoResponse);
        }
        return null;
    }

    @Override
    public Product deleteProduct(Long id){
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(id);
        FakeStoreProductDto fakeStoreProductDtoResponse = fakeStoreApiClient.deleteProduct(fakeStoreProductDto, id);
        if(fakeStoreProductDtoResponse != null){
            return from(fakeStoreProductDtoResponse);
        }
        return null;
    }

    private FakeStoreProductDto from(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null){
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}
