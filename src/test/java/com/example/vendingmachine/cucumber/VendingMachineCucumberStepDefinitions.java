package com.example.vendingmachine.cucumber;

import com.example.vendingmachine.dto.ProductEnum;
import com.example.vendingmachine.dto.VendingMachineResponseDto;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@ContextConfiguration(
//    classes = SpringBootCucumberTest.class,
//    loader = SpringApplicationContextLoader.class
//)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendingMachineCucumberStepDefinitions {

    @Autowired
    private VendingMachineHttpClient vendingMachineHttpClient;

    private ResponseEntity<?> response;

    private VendingMachineResponseDto getVendingMachineResponseBody() {
        return (VendingMachineResponseDto) this.response.getBody();
    }

    @Given("^getting vending machine response$")
    public void getting_vending_machine_response() {
        this.response = vendingMachineHttpClient.getStatus();
    }

    @Given("^response status \"(.*)\"$")
    public void responseStatus(String statusCode) {
        assertThat(String.valueOf(response.getStatusCode().value())).isEqualTo(statusCode);
    }

    @Given("^error message \"(.*)\"$")
    public void errorMessage(String errorMessage) {
        final String body = (String) response.getBody();
        assertThat(body).isEqualTo(errorMessage);
    }

    @Given("^user inserts coin \"(.*)\"$")
    public void insertCoin(String coin) {
        this.response = vendingMachineHttpClient.insertCoin(coin);
    }

    @Given("^user selects product \"(.*)\"$")
    public void userSelectsProduct(String productCode) {
        this.response = vendingMachineHttpClient.selectProduct(productCode);
    }

    @Given("^supplier resets operation$")
    public void supplierResetsOperation() {
        this.response = vendingMachineHttpClient.resetOperation();
    }

    @Given("^user cancelling the request$")
    public void userCancellingRequest$() {
        this.response = vendingMachineHttpClient.cancelRequest();
    }

    @Given("^the balance should be \"(.*)\"$")
    public void balance_is_equal(String balance) {
        assertThat(getVendingMachineResponseBody().getBalance()).isEqualTo(Integer.valueOf(balance));
    }

    @Given("^the change should be \"(.*)\"$")
    public void change_is_equal(String change) {
        assertThat(getVendingMachineResponseBody().getChange()).isEqualTo(Integer.valueOf(change));
    }

    @Given("^the selected product code should be \"(.*)\"$")
    public void selected_product_is_equal(String selectedProduct) {
        if ("null".equals(selectedProduct)) {
            assertThat(getVendingMachineResponseBody().getSelectedProduct()).isNull();
        } else {
            assertThat(getVendingMachineResponseBody().getSelectedProduct().getProductCode()).isEqualTo(selectedProduct);
        }
    }

    @Given("^selectable products size should be \"(.*)\"$")
    public void selectable_products_size(String size) {
        assertThat(getVendingMachineResponseBody().getSelectableProducts().size()).isEqualTo(Integer.valueOf(size));
    }

    @Given("^selectable products should contain product code \"(.*)\"$")
    public void selectable_products_should_contain(String productCode) {
        final Optional<ProductEnum> product = getVendingMachineResponseBody().getSelectableProducts().stream()
            .filter(p -> p.getProductCode().equals(productCode))
            .findFirst();
        assertThat(product.isPresent()).isTrue();
    }

    @Given("^available products size should be \"(.*)\"$")
    public void available_products_size(String size) {
        assertThat(getVendingMachineResponseBody().getAvailableProducts().size()).isEqualTo(Integer.valueOf(size));
    }

    @Given("^available products should contain product code \"(.*)\"$")
    public void available_products_should_contain(String productCode) {
        final Optional<ProductEnum> product = getVendingMachineResponseBody().getAvailableProducts().stream()
            .filter(p -> p.getProductCode().equals(productCode))
            .findFirst();
        assertThat(product.isPresent()).isTrue();
    }


}