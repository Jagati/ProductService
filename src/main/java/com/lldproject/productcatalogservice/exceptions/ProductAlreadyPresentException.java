package com.lldproject.productcatalogservice.exceptions;

public class ProductAlreadyPresentException extends RuntimeException {
    public ProductAlreadyPresentException(String message) {
        super(message);
    }
}
