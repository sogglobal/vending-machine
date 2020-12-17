package com.example.vendingmachine.application;

import com.example.vendingmachine.dto.CoinEnum;
import com.example.vendingmachine.dto.ProductEnum;
import com.example.vendingmachine.dto.VendingMachineResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendingMachineService {

    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public VendingMachineService(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    public VendingMachineResponseDto cancelRequest() {
        final Integer currentBalance = vendingMachineRepository.getBalance();

        vendingMachineRepository.resetBalance();

        VendingMachineResponseDto vendingMachineResponseDto = buildVendingMachineResponse();
        vendingMachineResponseDto.setChange(currentBalance);

        return vendingMachineResponseDto;
    }

    public VendingMachineResponseDto selectProduct(ProductEnum productEnum) {

        final Integer productEnumPrice = productEnum.getPrice();
        vendingMachineRepository.addOperation(-1 * productEnumPrice);

        final Integer currentBalance = vendingMachineRepository.getBalance();

        vendingMachineRepository.resetBalance();
        VendingMachineResponseDto vendingMachineResponseDto = buildVendingMachineResponse();
        vendingMachineResponseDto.setSelectedProduct(productEnum);
        vendingMachineResponseDto.setChange(currentBalance);

        return vendingMachineResponseDto;
    }

    public VendingMachineResponseDto resetOperation() {

        vendingMachineRepository.resetBalance();

        VendingMachineResponseDto vendingMachineResponseDto = buildVendingMachineResponse();

        return vendingMachineResponseDto;
    }

    public VendingMachineResponseDto insertCoin(CoinEnum coinEnum) {
        vendingMachineRepository.addOperation(coinEnum.getValue());

        VendingMachineResponseDto vendingMachineResponseDto = buildVendingMachineResponse();

        return vendingMachineResponseDto;
    }

    public VendingMachineResponseDto buildVendingMachineResponse() {
        Integer balance = vendingMachineRepository.getBalance();

        VendingMachineResponseDto vendingMachineResponseDto = new VendingMachineResponseDto();
        vendingMachineResponseDto.setBalance(balance);


        List<ProductEnum> selectableProducts = vendingMachineResponseDto.getAvailableProducts()
            .stream()
            .filter(product -> product.getPrice().compareTo(balance) <= 0)
            .collect(Collectors.toList());

        vendingMachineResponseDto.setSelectableProducts(selectableProducts);

        return vendingMachineResponseDto;
    }
}
