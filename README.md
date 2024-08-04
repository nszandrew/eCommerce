# eCommerce Backend

Este é o backend do projeto de eCommerce, construído usando Spring Boot. Ele fornece APIs para gerenciar produtos, usuários, pedidos e outras funcionalidades relacionadas ao eCommerce.

## Índice

- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [APIs](#apis)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Instalação

### Pré-requisitos

- Java
- Banco de dados

### Passos para instalação

1. Clone o repositório:

    ```bash
    git clone https://github.com/nszandrew/ecommerce-backend.git
    cd ecommerce-backend
    ```

2. Configure o banco de dados no arquivo `application.properties`:

    
```spring.datasource.url=jdbc:mysql://localhost:3306/${database}
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
springdoc.paths-to-match=/api/**
springdoc.swagger-ui.path=/swagger-ui.html

api.security.token.secret=${JWT_SECRET:my-secret-key}
```

Em seguida execute o projeto:

## Configuração

### application.properties

O arquivo `application.properties` contém as configurações do aplicativo. Configure as propriedades do banco de dados e outras configurações conforme necessário.

```properties
server.port=8080

# Database configuration
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# Swagger configuration
springdoc.paths-to-match=/api/**
springdoc.swagger-ui.path=/swagger-ui.html
```

## Uso

### Executando o servidor

Inicie a aplicacao:


O servidor estará disponível em `http://localhost:8080`.

### Acessando o Swagger UI

Para visualizar e testar as APIs, acesse o Swagger UI em:

```
http://localhost:8080/swagger-ui/index.html
```

## APIs

### Endpoints disponíveis

- **/api/products**: Endpoints for Admin People
  - `POST /api/products`: Post a Product
  - `PUT /api/user/admin`: Update User to Admin
  - SOON

- **/api/users**: Endpoint for Users
  - `PUT /api/update`: Update user info
  - `POST /api/register`: Register a new user
  - `POST /api/login`: User login
  - `GET /api/{id}`: Search one user by id
  - `DELETE /api/{id}`: Deleting a User

## Estrutura do Projeto

```plaintext
ecommerce-backend
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.ecommerce.firstversion
│   │   │   │   ├── ├── configuration
│   │   │   │   │   ├── controllers
│   │   │   │   │   ├── entities
│   │   │   │   │   ├── exceptions
│   │   │   │   │   ├── repositories
│   │   │   │   │   ├── service
│   │   │   │   │   ├── utils
│   │   │   │   │   └── EcommerceBackendApplication.java
│   │   ├── resources
│   │   │   ├── application.properties
│   │   │   └── db.migrations
│   └── test
│       └── java
│           └── com
│               └── ecommerce
│                   └── EcommerceBackendApplicationTests.java
│
├── .gitignore
├── pom.xml
└── README.md
```

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Fork o repositório
2. Crie uma branch para sua feature (`git checkout -b feature/feature-name`)
3. Commit suas alterações (`git commit -m 'Add some feature'`)
4. Faça o push para a branch (`git push origin feature/feature-name`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo para mais detalhes.
