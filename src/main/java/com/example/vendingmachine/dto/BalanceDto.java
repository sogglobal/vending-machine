package com.example.vendingmachine.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BalanceDto {

    private List<Integer> operations = new ArrayList<>();
    private Integer balance = 0;

    public BalanceDto() {
        resetBalance();
    }

    public void resetBalance() {
        this.operations.clear();
        this.balance = 0;
    }

    public void addOperation(Integer value) {
        this.operations.add(value);

        final Integer newBalance = getOperations().stream()
            .reduce(0, (coin1, coin2) -> coin1 + coin2);

        setBalance(newBalance);
    }

    public List<Integer> getOperations() {
        return Collections.unmodifiableList(this.operations);
    }

    public Integer getBalance() {
        return balance;
    }

    private void setBalance(Integer balance) {
        this.balance = balance;
    }
}
