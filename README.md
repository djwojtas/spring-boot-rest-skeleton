# spring-boot-rest-skeleton

### Structure

This project contains the basic structure to start building a RESTful API with SpringBoot. As you can see in the following diagram, the project contains three main parts:

1. **Controller**: it will be the attendant of serve all the requests which are made to our API. Its main object will be delegate those requests to the corresponding service and serve the response or error as appropiate.
2. **Service**: it will interact with de appropiate DAO or maybe with another service from any other system in orden to generate the response to the request.
3. **DAO/Service**: it will obtain the necessary data to generate the response, either from our own database or from another system.

<p align="center">
<img src="https://cloud.githubusercontent.com/assets/6959226/15093182/ed29ef6e-147f-11e6-993f-4882fb9533a4.png">
</p>

### Features
* All the requests are securized by [OAuth 2.0](http://oauth.net/2/)
* This project uses a H2 database which stores the access/refresh tokens and the user data. You can view it's content using the H2 console deployed at [http://localhost:8080/h2console](http://localhost:8080/h2console)

### How-To use

##### 1. Obtaining an access token
_Request_
````
POST /oauth/token HTTP/1.1
Host: localhost:8080
Authorization: Basic <basic_token>
````

_Body (form-data)_
```
grant_type = password
username = <username>
password = <password>
````

_Response_
```json
{
  "access_token" : "5eeb638a-b35f-4620-8cdc-107224ad630a",
  "token_type" : "bearer",
  "refresh_token" : "695d29c9-08b8-4288-8baf-ef56c2ef0c78",
  "expires_in" : 3599,
  "scope" : "read write"
}
```
##### 2. Obtaining a refresh token
_Request_
````
POST /oauth/token HTTP/1.1
Host: localhost:8080
Authorization: Basic <basic_token>
````

_Body (form-data)_
```
grant_type = refresh_token
refresh_token = <refresh_token>
````

_Response_
```json
{
  "access_token": "7fde433f-694e-4c05-9c0f-3a510003ae49",
  "token_type": "bearer",
  "refresh_token": "832e10f0-5c54-44f6-8928-ea246f3f4ec2",
  "expires_in": 3599,
  "scope": "read write"
}
```

##### 3. Invalidating an access token

_Request_
```
GET /oauth/logout HTTP/1.1
Host: localhost:8080
Authorization: Bearer <access_token>
```

##### 4. Accessing to a securized path
_Request_
````
GET /api/v1/helloWorld HTTP/1.1
Host: localhost:8080
Authorization: Bearer <access_token>
```

_Response_
````
Hello World!
```

### Changelog

##### 1.0.0.RELEASE
Initial version

### License
This project is licensed under a [Creative Commons Attribution-ShareAlike 4.0 International License](https://creativecommons.org/licenses/by-sa/4.0/)
