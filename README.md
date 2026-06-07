# 🧺 Família Connect — Backend

> API REST para gerenciamento de doações de cestas básicas para famílias carentes.

---

## 📋 Sobre o Projeto

O **Família Connect** é um sistema desenvolvido para facilitar a gestão e distribuição de cestas básicas a famílias em situação de vulnerabilidade social. O backend fornece uma API REST completa para controlar cadastros de famílias, pessoas, funcionários e entregas, garantindo rastreabilidade e organização no processo de doação.

### Principais funcionalidades

- Cadastro e gerenciamento de **famílias** beneficiárias
- Cadastro de **pessoas** vinculadas a cada família
- Gerenciamento de **funcionários** responsáveis pelas operações
- Controle de **entregas** de cestas básicas
- Autenticação e autorização via **JWT**
- Documentação interativa da API com **Swagger / OpenAPI**

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework principal |
| Spring Data JPA | — | Persistência de dados |
| Spring Security | — | Autenticação e autorização |
| Spring Validation | — | Validação de dados |
| Spring Cloud OpenFeign | 2025.1.0 | Comunicação entre serviços |
| MySQL | — | Banco de dados relacional |
| JWT (jjwt) | 0.12.5 | Tokens de autenticação |
| Lombok | 1.18.46 | Redução de boilerplate |
| SpringDoc OpenAPI | 2.8.6 | Documentação Swagger |
| Spring Dotenv | 4.0.0 | Gerenciamento de variáveis de ambiente |
| Docker | — | Containerização |
| Maven | — | Gerenciamento de dependências |

---

## 📁 Estrutura do Projeto

```
Familia-Connect-Back/
├── .github/
│   └── workflows/          # Pipelines de CI/CD
├── src/
│   └── main/
│       ├── java/school/sptech/FamiliaConnect/
│       │   ├── controller/     # Endpoints REST
│       │   ├── service/        # Regras de negócio
│       │   ├── repository/     # Acesso ao banco de dados
│       │   ├── entity/         # Entidades JPA
│       │   ├── dto/            # Objetos de transferência de dados
│       │   ├── config/         # Configurações (Security, OpenAPI, etc.)
│       │   └── exception/      # Tratamento de exceções
│       └── resources/
│           └── application.properties
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

---

## ⚙️ Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- [Java 21+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [MySQL 8+](https://dev.mysql.com/downloads/) *(para execução local sem Docker)*
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/) *(para execução via container)*

---

## 🚀 Como Iniciar

### 1. Clone o repositório

```bash
git clone https://github.com/fsFernando072/Familia-Connect-Back.git
cd Familia-Connect-Back
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com base no exemplo abaixo:

```env
DB_URL=jdbc:mysql://localhost:3306/familia_connect
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
DB_TYPE_DDL=update
```

> **Dica:** O valor `DB_TYPE_DDL` pode ser `create`, `create-drop`, `update` ou `validate`. Para o primeiro uso, utilize `create` ou `update`.

---

### ▶️ Opção A — Executar com Maven (local)

> Certifique-se de ter um banco de dados MySQL rodando e configurado no `.env`.

```bash
./mvnw spring-boot:run
```

Ou no Windows:

```bash
mvnw.cmd spring-boot:run
```

---

### 🐳 Opção B — Executar com Docker Compose

```bash
docker-compose up --build
```

> A aplicação estará disponível em: **http://localhost:8080**

Para parar os containers:

```bash
docker-compose down
```

---

### 🔨 Opção C — Gerar o JAR e executar manualmente

```bash
./mvnw clean package -DskipTests
java -jar target/FamiliaConnect-1.0.0.jar
```

---

## 📖 Documentação da API

Com a aplicação rodando, acesse a documentação interativa do Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

Ou via OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação. Para acessar os endpoints protegidos:

1. Realize o login no endpoint de autenticação para obter o cookie com o token.
2. Inclua o token do cookie quando chamar os demais endpoints.

---

## 🧪 Testes

Para rodar os testes automatizados:

```bash
./mvnw test
```

---

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature: `git checkout -b feature/minha-feature`
3. Commit suas alterações: `git commit -m 'feat: adiciona minha feature'`
4. Push para a branch: `git push origin feature/minha-feature`
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
