package com.example.vendingmachine.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum CoinEnum {

    penny(1),
    nickel(5),
    dime(10),
    quarter(25);

    private final Integer value;

    CoinEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name() + " (" + getValue() + ")";
    }

    public static Optional<CoinEnum> getCoinByValue(Integer value) {
        return Arrays.stream(CoinEnum.values())
            .filter(coin -> coin.getValue().equals(value))
            .findFirst();
    }

}
