# password-manager

### Dependencies and Libraries
- Java 11
- Spring Boot
- JPA
- Lombok
- Database h2 
 You can access the data on http://localhost:8080/h2-ui/

### Limitations:
 - To reduce the complexity I'm not using Spring Security which could be implemented together with Oauth2
 - Password is not being encrypted
 
### Rotas: 

GET  http://localhost:8080/password-cards

GET http://localhost:8080/password-cards?name='name'

GET http://localhost:8080/password-cards/{id}

POST http://localhost:8080/password-cards/

PUT http://localhost:8080/password-cards/{id}

DELETE http://localhost:8080/password-cards/{id}

