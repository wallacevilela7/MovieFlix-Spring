# MovieFlix 🎬🔐

Bem-vindo ao **MovieFlix**, uma aplicação backend criada com o objetivo de gerenciar um catálogo de filmes e streamings. Contando com a parte de  autenticação e autorização via **JWT**, protegendo recursos sensíveis da aplicação.

## **Funcionalidades** 🚀

- **Autenticação com JWT**: Acesso seguro com login baseado em token.
- **Cadastro de Usuários**: Inclusão de novos usuários (via carga inicial ou admin).
- **Listagem de Filmes**: Visualização de filmes com paginação e filtro por gênero.
- **Detalhes do Filme**: Página exclusiva com informações completas do filme.
- **Avaliações**: Usuários autenticados podem comentar e avaliar os filmes.


## **Regras de Negócio** 📌

- **Autenticação obrigatória** para acessar qualquer endpoint, exceto os de login e cadastro.
- **Usuários autenticados** podem:
    - Visualizar a lista de filmes e detalhes individuais.
    - Avaliar filmes com comentários.
- **Usuários com perfil de ADMIN** têm permissões adicionais para:
    - **Gerenciar filmes**, incluindo criação, edição e exclusão.
    - **Gerenciar categorias (gêneros)** de filmes.
    - **Gerenciar streamings** vinculados aos filmes.
- Toda alteração (criação, edição ou exclusão) de filmes, categorias e streamings é protegida por regras de autorização com base no perfil do usuário.
- Cada avaliação é registrada com a referência do usuário autenticado e o filme correspondente.


## **Tecnologias Utilizadas** 🛠️

- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker e Docker Compose**
- **Maven**
- **Swagger**


## **Endpoints da API** 🌍

### **Login**
- **POST** `/oauth/token`
- **Headers**:
  ```http
  Authorization: Basic base64(client_id:client_secret)
  Content-Type: application/x-www-form-urlencoded
  ```
- **Body**:
  ```plaintext
  username=usuario@email.com&password=123456
  ```
- **Response**:
  ```json
  {
    "access_token": "JWT_TOKEN_AQUI",
    "token_type": "bearer",
    ...
  }
  ```

### **Listagem de Filmes**
- **GET** `/movies?genreId=0&page=0&size=12`

### **Detalhes de um Filme (Protegido)**
- **GET** `/movies/{id}`
- **Authorization**: `Bearer <JWT_TOKEN>`

### **Inserir Avaliação (MEMBER)**
- **POST** `/reviews`
- **Body**:
  ```json
  {
    "movieId": 1,
    "text": "Excelente filme!"
  }
  ```

## **Configuração do Banco de Dados** 🗄️

Utilizamos **PostgreSQL**, podendo ser iniciado via **Docker Compose**. A aplicação se conecta através das configurações presentes no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/movieflix
spring.datasource.username=postgres
spring.datasource.password=senha
```

Execute:

```bash
docker-compose up
```

## **Como Executar o Projeto** ▶️

### Passo 1: Clone o repositório

```bash
git clone https://github.com/wallacevilela7/MovieFlix.git
cd MovieFlix
```

### Passo 2: Backend

```bash
cd backend
./mvnw spring-boot:run
```

A API estará disponível em **http://localhost:8080**.

### Passo 3: Frontend

```bash
cd frontend
npm install
npm start
```

A interface estará acessível em **http://localhost:3000**.

---

## **Contribuição** 🤝

Fique à vontade para abrir **issues** ou enviar um **pull request** com melhorias. Toda contribuição é bem-vinda!