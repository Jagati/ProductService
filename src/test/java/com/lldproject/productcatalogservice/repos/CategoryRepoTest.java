package com.lldproject.productcatalogservice.repos;

import com.lldproject.productcatalogservice.models.Category;
import com.lldproject.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    //@Transactional
    public void testFetchTypesAndModes() {
        Optional<Category> categoryOptional =
                categoryRepo.findById(138L);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            //Case where we are asking for child entity
//            for(Product product : category.getProducts()) {
//                System.out.println(product.getName());
//            }
        }
    }

    @Test
    @Transactional
    public void NPlusOne() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category :categoryList) {
            for (Product product :category.getProducts()) {
                System.out.println(product.getName());
            }
        }

    }

}
