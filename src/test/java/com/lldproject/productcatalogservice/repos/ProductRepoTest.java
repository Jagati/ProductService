package com.lldproject.productcatalogservice.repos;

import com.lldproject.productcatalogservice.models.Category;
import com.lldproject.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void addRecordsToRDSInstance() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Jagati Tata");

        Product product2  = new Product();
        product2.setId(2L);
        product2.setName("Vani Agarwal");

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Sunil Kumar");

        Category category= new Category();
        category.setId(20L);
        category.setName("Learners");
        product.setCategory(category);
        product2.setCategory(category);
        product3.setCategory(category);

        productRepo.save(product);
        productRepo.save(product2);
        productRepo.save(product3);
    }


    //@Test
    //@Transactional
    public void testRepoMethods() {
        //List<Product> productList = productRepo.findAllByOrderByPrice();
        //System.out.println(productList.get(0).getName());
        //System.out.println(productList.get(productList.size()-1).getName());
        System.out.println(productRepo.getProductNameByProductId(33L));
    }

}
