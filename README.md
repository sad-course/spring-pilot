## SPRING PILOT

Simple initial spring project for first steps in the framework with Spring Boot. :rocket:


### Running the project

Install the dependencies
```
mvn clean install
``` 


Run the project
```
mvn spring-boot:run
``` 

Test the endpoints that says hello!

The body content is returned like:

```
{
    "id":number,
    "content": "Text here"
}
```


http://localhost:8080/hello

and one for says hello for your name

http://localhost:8080/hello/for%20your%20name


### Product management Example
### Endpoints for in-memory objects

POST http://localhost:8080/products
```
{
    "name": "Caju",
    "price": 21,
    "quantity": 100
}
```

GET http://localhost:8080/products/1/
```
{
    "name": "Caju",
    "price": 21,
    "quantity": 100
}
```

PATCH http://localhost:8080/products/1/
```
{
    "price": 25
}
```

DELETE http://localhost:8080/products/1/
```
Product with id 1 was deleted successfully
```

GET http://localhost:8080/products/search?name=caju
```
{
    "name": "Caju",
    "price": 21,
    "quantity": 100
}
```