package com.example.vendingmachine.application;

import com.example.vendingmachine.dto.BalanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VendingMachineRepository {

    private final BalanceDto balanceDto;

    @Autowired
    public VendingMachineRepository() {
        balanceDto = new BalanceDto();
    }

    public Integer addOperation(Integer value) {

        getBalanceDto().addOperation(value);

        return getBalanceDto().getBalance();
    }

    public Integer getBalance() {
        return getBalanceDto().getBalance();
    }

    public void resetBalance() {
        getBalanceDto().resetBalance();
    }

    public BalanceDto getBalanceDto() {
        return balanceDto;
    }
}
