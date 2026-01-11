package com.lldproject.productcatalogservice.clients;

import com.lldproject.productcatalogservice.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    //Custom method created for all put operations - write, update, delete, etc. It is similar to REST template methods getForEntity, postForEntity
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public FakeStoreProductDto getProductById(Long id){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{id}", null, FakeStoreProductDto.class, id);
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }

    public FakeStoreProductDto updateProduct(FakeStoreProductDto productDto, Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", productDto, FakeStoreProductDto.class, id);
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }

    public FakeStoreProductDto[] getAllProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos;
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products", null, FakeStoreProductDto[].class);
        if(validateResponses(fakeStoreProductDtoResponseEntity)){
            fakeStoreProductDtos = fakeStoreProductDtoResponseEntity.getBody();
            return fakeStoreProductDtos;
        }
        return null;
    }

    public FakeStoreProductDto deleteProduct(FakeStoreProductDto productDto, Long id){
        ResponseEntity<FakeStoreProductDto> deleteSuccessEntity = requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{id}", productDto, FakeStoreProductDto.class, id);
        if(validateResponse(deleteSuccessEntity)){
            return deleteSuccessEntity.getBody();
        }
        return null;
    }

    private Boolean validateResponse(ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity){
        if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().
                        equals(HttpStatusCode.valueOf(200))){
            return true;
        }
        return false;
    }

    private Boolean validateResponses(ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity){
        if(fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().
                        equals(HttpStatusCode.valueOf(200))){
            return true;
        }
        return false;
    }

}
