# Gestor de Campanhas
### Introdução

Esse sistema tem como objetivo realizar a gestão de campanhas e o cadastramento de usuários para utilização dessas campanhas.

Foram criados dois microserviços: um para o CRUD de campanhas e outro para o cadastramento dos usuários.

Esse projeto foi criado utilizando maven com 2 sub módulos centralizando as dependências em um módulo principal

Foram utilizados os seguintes frameworks e ferramentas:

- Spring -> Para a criação do ambiente web
- JPA    -> Para realização da persistência dos dados
- H2     -> Para armazenamento dos dados
- Fasterxml -> Para conversão dos dados em JSON
- Lombok -> Para reduzir a verbosidade do código
- Hystrix -> Para criação de fallback
- Feign Cliente -> Para chamadas a outros microserviços
- RabbitMq -> para criação de filas a fim de avisar outros sistemas
- Swagger -> Para documentação dos microserviços
- Junit   -> para criação dos testes unitários
- Fixture Factory -> Para criação de templates

Como foi utilizado um banco de dados em memória não foram criados fallbacks para tal integração o unico fallback existente
é o tratamento de chamadas entre os microserviços.

### Pre-requisitos

- É necessário ter o Java 8 e maven instalado e configurado.

### Build

Para realizar o build é necessário compilar o projeto:

```sh
$ mvn compile
```
### Iniciando os Serviços

Para iniciar o serviço de campanhas realize os seguintes comandos:

```sh
$ cd campanha-service
$ mvn compile spring-boot:run
```

Para iniciar o serviço de usuário realize os seguintes comandos:

```sh
$ cd socio-torcedor-service
$ mvn compile spring-boot:run
```

Você pode parar o serviço de campanhas e continuar utilizando o serviço de usuário, porém as campanhas não serão associadas.

### Testes

Para verificar os testes unitários do serviço de campanha realize os seguintes comandos:

```sh
$ cd campanha-service
$ mvn clean test
```

Para verificar os testes unitários do serviço de usuário realize os seguintes comandos:

```sh
$ cd socio-torcedor-service
$ mvn clean test
```
