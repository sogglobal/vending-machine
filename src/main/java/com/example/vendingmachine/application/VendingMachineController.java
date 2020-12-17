package com.example.vendingmachine.application;

import com.example.vendingmachine.dto.CoinEnum;
import com.example.vendingmachine.dto.ProductEnum;
import com.example.vendingmachine.dto.VendingMachineResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.vendingmachine.application.VendingMachineController.*;

@RestController
@RequestMapping(API_VENDING_MACHINE)
public class VendingMachineController {

    public static final String API_VENDING_MACHINE = "/api/vending-machine";

    private VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping
    public VendingMachineResponseDto getStatus() {
        return vendingMachineService.buildVendingMachineResponse();
    }

    @PostMapping("/insert-coin/{coin}")
    public ResponseEntity<?> addPenny(@PathVariable(name = "coin") Integer coin) {
        Optional<CoinEnum> coinByValue = CoinEnum.getCoinByValue(coin);
        if (coinByValue.isPresent()) {
            VendingMachineResponseDto vendingMachineResponseDto = vendingMachineService.insertCoin(coinByValue.get());
            return ResponseEntity.ok(vendingMachineResponseDto);
        }

        final String availableCoins = Arrays.asList(CoinEnum.values()).stream()
            .map(coinEnum -> String.valueOf(coinEnum.getValue()))
            .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body("Wrong coin value. Acceptable coins are: " + availableCoins);
    }

    @PostMapping("/select-product/{product}")
    public ResponseEntity<?> selectProduct(@PathVariable(name = "product") String product) {
        VendingMachineResponseDto vendingMachineResponseDto = vendingMachineService.buildVendingMachineResponse();

        final List<ProductEnum> selectableProducts = vendingMachineResponseDto.getSelectableProducts();

        final String productsString = selectableProducts.isEmpty() ? "none." :
            selectableProducts.stream()
                .map(p -> p.getProductCode())
                .collect(Collectors.joining(", "));

        Optional<ProductEnum> productByCode = ProductEnum.getProductByCode(product);
        if (productByCode.isPresent()) {
            final ProductEnum productEnum = productByCode.get();

            if (selectableProducts.contains(productEnum)) {
                vendingMachineResponseDto = vendingMachineService.selectProduct(productEnum);

                return ResponseEntity.ok(vendingMachineResponseDto);
            } else {
                return ResponseEntity.badRequest().body("Product '" + product + "' could not be selected. Acceptable product codes are: " + productsString);
            }
        }

        return ResponseEntity.badRequest().body("Wrong product value '" + product + "'. Acceptable product codes are: " + productsString);
    }

    @PostMapping("/cancel-request")
    public VendingMachineResponseDto cancelRequest() {
        VendingMachineResponseDto vendingMachineResponseDto = vendingMachineService.cancelRequest();

        return vendingMachineResponseDto;
    }

    @PostMapping("/reset-operation")
    public VendingMachineResponseDto resetOperation() {
        VendingMachineResponseDto vendingMachineResponseDto = vendingMachineService.resetOperation();

        return vendingMachineResponseDto;
    }


}
