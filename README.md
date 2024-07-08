# PbAbrilDes3_MariaClaraBatistaDosSantosthrough the construction of a microservices architecture
This repository contains the third challenge of the UOL compass knowledge trail, the main objective was to demonstrate my knowledge of SpringBoot, database and Amazon AWS through the construction of a microservices architecture.

# Tecnology used 
Java 17
Spring Boot
Spring Data JPA
Hibernate
Swagger for API documentation
Maven for dependency Management
Docker and docker-compose
MySQL database
Insomnia
Amazon RDS
JaCoCo plugin

# Installing and running

1: First of all, theres is a need to clone this repository in your terminal, by command:
 git clone https://github.com/Mary-cb/PbAbrilDes3_MariaClaraBatistaDosSantos.git
2: You must acess the project folder/directory
3: Open the project in your IDE
4: Set Up your JDK
5: Before leading to the final step you have to configure your database settings into the file "application.yml" wich is located in RESOURCES folder 
6: If using docker be aware to adjust also the DockerCompose file
7: Last step is to compile and execute the program by the steps: MAVEN -> PLUGINS -> SPRINGBOOT -> SPRINGBOOT: RUN

# Documentation 
- Access the documentation in the following URLs:
MSCUSTOMER: http://localhost::8082/swagger-ui/index.html
MSPAYMENT: http://localhost::8083/swagger-ui/index.html
MSCALCULATE: http://localhost::8081/swagger-ui/index.html

# MICROSERVICES ENDPOINTS

# CUSTOMER MICROSERVICE
GET /v1/customers - Retrieve customer details based on their ID.
POST /v1/customers - Register a new customer.
PUT /v1/customers - Update the information of a customer based on their ID.
DELETE /v1/customers - Delete a customer based on their ID.

# PAYMENT MICROSERVICE
GET /v1/payment - Retrieve payment details based on its ID.
GET /v1/payment/customer-id - Retrieve payment details based on the referee customer ID.
POST /v1/payment - Register a new payment.

# CALCULATE MICROSERVICE
GET /v1/rules - Retrieve a rule details based on its ID.
POST /v1/rules - Create a new rule.
PUT /v1/rules - Update the information of a rule based on its ID.
DELETE /v1/rules - Delete a rule based on its ID.
POST /v1/calculate - Calculate the amount of points a customer will recieve based on the value of payment.

# Author 
Maria Clara Batista dos Santos - Junior Java backend developer

# License
Permission only for educational purposes


