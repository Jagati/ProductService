package com.lldproject.productcatalogservice.repos;

import com.lldproject.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    List<Product> findProductByPriceBetween(Double low,Double high);
    List<Product> findAllByOrderByPrice();
    @Query("SELECT p.name from Product p where p.id=:id")
    String getProductNameByProductId(Long id);

}
