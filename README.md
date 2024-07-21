# Shoe Store E-commerce Backend

This is the backend for a shoe e-commerce website, built with Spring Boot and MongoDB.

## System Requirements

- Java JDK 21 or higher
- Maven 3.6.3 or higher
- MongoDB 4.4 or higher

## Installation

1. Clone the repository:
using command 
``` bash
git clone https://github.com/Roizk/ShoeStore.git
```
2. Navigate to the project directory: cd shoe-store-backend
3. Configure MongoDB connection [How to get connection string](https://www.mongodb.com/resources/products/fundamentals/mongodb-connection-string):
   Open `src/main/resources/application.yml` and update the MongoDB URI:
```yaml
spring:
  data:
    mongodb:
      uri: your_mongodb_uri
      database: ShoeStore 
```
4. Configure email settings [How to get your SMTP gmail](https://www.gmass.co/blog/gmail-smtp/) :
In the same application.yml file, update the email information:
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your_email@gmail.com
    password: your_email_password
```
5. Configure jwt:
```yaml
jwt:
  secret-key: your secret-key
```
6. Build the project: mvn clean install
