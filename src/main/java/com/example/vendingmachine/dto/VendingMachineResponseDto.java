package com.example.vendingmachine.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachineResponseDto {

    private final List<ProductEnum> availableProducts = Arrays.asList(ProductEnum.values());
    private List<ProductEnum> selectableProducts = new ArrayList<>();
    private ProductEnum selectedProduct;

    private Integer balance = 0;
    private Integer change = 0;

    public VendingMachineResponseDto() {
    }

    public List<ProductEnum> getAvailableProducts() {
        return availableProducts;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }

    public List<ProductEnum> getSelectableProducts() {
        return selectableProducts;
    }

    public void setSelectableProducts(List<ProductEnum> selectableProducts) {
        this.selectableProducts = selectableProducts;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer change) {
        this.change = change;
    }

    public ProductEnum getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEnum selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
