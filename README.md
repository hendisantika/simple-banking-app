# Simple Banking

###### Simple core banking example, only creating account and doing transaction

## Requirements

This project consist of implementing a small core banking solution:

* Account to keep track of current accounts, balances, and transaction history
* Capability to publish messages into RabbitMQ for other consumers

Account

* Account service keeps track of accounts, their balances, and transactions.
* Account service must publish all insert and update operations to RabbitMQ.

* REST APIs:
    - Create account
        - API must create balances for the account in given currencies.
        - Input: Customer ID, Country, List of currencies (allowed values are EUR, SEK, GBP, USD)
        - Output: Account ID, Customer ID, List of balances - (Available amount, Currency)
        - Errors: Invalid currency
    - Get account
        - Return the account object.
        - Input: Account ID
        - Output: Account ID, Customer ID , List of balances - (Available amount , Currency)
        - Input: Account not found

    - Create transaction
        - Create a transaction on the account and return the transaction object.
        - Input: Account ID , Amount , Currency , Direction of transaction (IN, OUT), Description
        - Output: Account ID , Transaction ID , Amount , Currency , Direction of transaction , Description , Balance
          after
          transaction
        - Errors: Invalid currency, Invalid direction, Invalid amount (if negative amount for example), Insufficient
          funds,
          Account missing, Description missing
        - Transactions with direction IN must increase account balance with the transaction amount and transactions with
          direction OUT must decrease the balance of account in respective currency.

    - Get transaction
        - Return a list of transactions
        - Input: Account ID
        - Output: Account ID , Transaction ID , Amount , Currency , Direction of transaction , Description
        - Errors: Invalid account

## Technologies used

* Java 11
* Spring boot 2.7.12
* MyBatis 2.3.1
* Gradle 7.6.1
* JUnit 5.8.2
* Postgres 15.3
* RabbitMQ 3.11
* Docker 24.0.1

## Build Project

`./mvnw clean spring-boot:run`

It will create jar file under target/** directory.

## Run project

`docker compose up --build`

Up all instances (db with structure, app, rabbit)

## Test coverage

Class - 96%, Method - 76%, Line - 75%

## Important choices

- In this project I focus more on design rather implementing method only.
- I tried to touch every aspect of application (but still it's not cover most aspects that we face in production)
- With docker, it is just two command - one for build & another for run, it will run same environment as in production
- To write query and generate result map, I have used xml based mapping.
- Basic validation done in form and form validator.
- Global exception handler is used for handling all exceptions.
- For financial amount calculation, I have used BigDecimal.
- All MyBatis xml mapper called through interface without sql session factory.
- A util class added for helper methods.
- RabbitMQ is access through a simple class and only simple method exposed for message publish.

## Performance

- I run 1000 iteration for each query via Postman collections. At first, it fast, but when more data inserted and load
  more transaction list, it takes time. 4 APIs / 1000 iterations took 52s. If we do pagination for transaction (which
  is a must, for short time I didn't add it) it will improve performance significantly. (Because, I inserted same
  transaction for same account. So, when load transaction, after 100 iteration, it returns 100+ items for rest 900
  request)
- So, per second 19 req average, mix api

## Scalability

As it dockerize microservices, its easy to scale horizontally. We can run multiple instances of our microservices and
to access this (diverting request among all instances) we can use load balancer.
