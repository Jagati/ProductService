package com.lldproject.productcatalogservice.services;

import com.lldproject.productcatalogservice.dtos.SortParam;
import com.lldproject.productcatalogservice.dtos.SortType;
import com.lldproject.productcatalogservice.models.Product;
import com.lldproject.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams) {
//        Sort sortById = Sort.by("id").descending();
//        Sort sort = Sort.by("price").descending();

        Sort sort =null;

        if(!sortParams.isEmpty()) {
            if (sortParams.get(0).getSortType().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(0).getParamName());
            } else {
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
            }
        }

        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()));
            }else {
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
            }
        }

        return productRepo.findByName(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}

//{
//   "price" , "ascending",
//   "rating" , "descending",
//   "id" : "ascensding"
//}
