# warehouse-service
Service provides creation and fetch of warehouse boxes.

## Assumptions
- Products are unique and locations can be reused

## Features

- User should be able to create warehouse boxes
- User should be able to create warehouse products
- Get all warehouse boxes details based on search query parameter
- Used Spring boot to create the microservice and JPA to store and fetch data from database.
- Used in-memory H2 database.


## Instructions

#### TODOs

- Implement angular form to create a product(Currently set of locations and products are added to db on start of application)
- Add additional tests to the `BoxService.java` to check the search and sorting


#### Run service locally

Run warehouse service > ./gradlew bootRun
Run warehouse app in the path (warehouse-service\src\main\resources\webapp\warehouse-app>) > ng serve

Single page application will be available here -> http://localhost:4200/add-box

#### Run services using Docker

Use docker images which are pushed to docker hub
Backend - https://hub.docker.com/repository/docker/merlinchacko/warehouse-service-backend
 run -> docker run -e JASYPT_PASSWORD='merlin' --name="warehouse-service-backend" --publish 8080:8080
frontend - https://hub.docker.com/repository/docker/merlinchacko/warehouse-service-frontend
 run -> docker run  --name="warehouse-service-frontend" --publish 9001:4200

To run with one command use -> `docker compose up` (not working currently)


## Operations

Swagger UI: http://localhost:8080/swagger-ui.html

### Create a Box

`POST` `/warehouse/box`
```json

{
  "description": "Box-1",
  "locationId": 1,
  "productId": 1
}
```

### Retrieve a Box

`GET` `/warehouse/box/search?query='query'`

This feature retrieves the Box data(product name and location) from the database based on query parameter.

### Create a Product

`POST` `/warehouse/product`
```json

{
  "id": 1
  "name": "product"
}
```



