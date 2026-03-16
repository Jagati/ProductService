package com.lldproject.productcatalogservice.services;

import com.lldproject.productcatalogservice.dtos.SortParam;
import com.lldproject.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParamList);
}
