package com.example.vendingmachine.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
    plugin = {"pretty", "html:target/cucumber/vendingmachine"},
    tags = {"@Integration"}
)
public class CucumberIntegrationTest {
}
