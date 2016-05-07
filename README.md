# spring-boot-rest-skeleton

This project contains the basic structure to start building a RESTful API with Spring Boot. As you can see in the following diagram the project is structured in three main parts:

1. **Controller**: it will be the attendant of all the requests which be made to our API. It's main object will be delegate those requests to the corresponding service and serve the response or error as appropiate.
2. **Service**: it will interact with de appropiate DAO or maybe with another service from other system in orden to generate the response to the request.
3. **DAO/Service**: it will obtain the necessary data to generate the response, either from our own database or from another system.

<p align="center">
<img src="https://cloud.githubusercontent.com/assets/6959226/15093182/ed29ef6e-147f-11e6-993f-4882fb9533a4.png">
</p>

## Changelog

### 1.0.0.RELEASE
Initial version
