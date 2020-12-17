package com.example.vendingmachine.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum ProductEnum {

    Coke("coke", 25),
    Pepsi("pepsi", 35),
    Soda("soda", 45);

    private final String productCode;
    private final Integer price;

    ProductEnum(String productCode, Integer price) {
        this.productCode = productCode;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public Integer getPrice() {
        return price;
    }

    public static Optional<ProductEnum> getProductByCode(String productCode) {
        return Arrays.stream(ProductEnum.values())
            .filter(product -> product.getProductCode().equals(productCode))
            .findFirst();
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name() + " - product code [" + getProductCode() + "] price [" + getPrice() + "]";
    }

}
