# Demo Rest API
This is a very basic project to use for demos and starter project for tutorials.

Requires:
- Java 11
- Maven
- Docker

## Run
`docker-compose up`

`mvn clean package`

`mvn spring-boot:run`


## Docker

`mvn clean package`

`docker build -t demo-api .`

`docker run -p 8080:8080 demo-api`


## Test

`curl http://localhost:8080/products`

or

`curl http://localhost:8080/products/1`
