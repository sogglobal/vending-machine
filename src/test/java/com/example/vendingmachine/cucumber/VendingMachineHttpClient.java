package com.example.vendingmachine.cucumber;

import com.example.vendingmachine.application.VendingMachineController;
import com.example.vendingmachine.dto.VendingMachineResponseDto;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class VendingMachineHttpClient {

    private final String SERVER_URL = "http://localhost";

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();


    private String vendingMachineApi() {
        return SERVER_URL + ":" + port + VendingMachineController.API_VENDING_MACHINE;
    }

    public ResponseEntity<VendingMachineResponseDto> getStatus() {
        return restTemplate.getForEntity(vendingMachineApi(), VendingMachineResponseDto.class);
    }

    public ResponseEntity<?> insertCoin(String coin) {
        try {
            final String url = vendingMachineApi() + "/insert-coin/" + coin;
            return restTemplate.postForEntity(url, null, VendingMachineResponseDto.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    public ResponseEntity<?> selectProduct(String productCode) {
        try {
            final String url = vendingMachineApi() + "/select-product/" + productCode;
            return restTemplate.postForEntity(url, null, VendingMachineResponseDto.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    public ResponseEntity<VendingMachineResponseDto> cancelRequest() {
        final String url = vendingMachineApi() + "/cancel-request";
        return restTemplate.postForEntity(url, null, VendingMachineResponseDto.class);
    }

    public ResponseEntity<VendingMachineResponseDto> resetOperation() {
        final String url = vendingMachineApi() + "/reset-operation";
        return restTemplate.postForEntity(url, null, VendingMachineResponseDto.class);
    }


}
