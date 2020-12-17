# Cucumber in Spring Boot 

## Write a program to design Vending Machine using your 'favourite language' with all possible tests

1.    Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.

2.    Allow user to select products Coke(25), Pepsi(35), Soda(45) + Return selected product and remaining change if any.

3.    Allow user to take refund by cancelling the request.

4.    Allow reset operation for vending machine supplier.

## API automation

Write Cucumber regression tests to cover all paths


## How to run

````
mvn spring-boot:run
````

### Get vending machine status
````
GET http://localhost:8080/api/vending-machine

Accept: application/json
````

### Insert coin 1
````
POST http://localhost:8080/api/vending-machine/insert-coin/1

Accept: application/json
````

### Insert coin 5
````

POST http://localhost:8080/api/vending-machine/insert-coin/5

Accept: application/json
````

### Insert coin 10

````
POST http://localhost:8080/api/vending-machine/insert-coin/10

Accept: application/json
````


### Insert coin 25
````
POST http://localhost:8080/api/vending-machine/insert-coin/25

Accept: application/json
````

### Select product coke
````
POST http://localhost:8080/api/vending-machine/select-product/coke

Accept: application/json
````

### Select product pepsi
````
POST http://localhost:8080/api/vending-machine/select-product/pepsi

Accept: application/json
````

### Select product soda
````

POST http://localhost:8080/api/vending-machine/select-product/soda

Accept: application/json
````

### Cancel request
````

POST http://localhost:8080/api/vending-machine/cancel-request

Accept: application/json
````

### Reset operation
````

POST http://localhost:8080/api/vending-machine/reset-operation

Accept: application/json
````

###
