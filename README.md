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
│   ├── main/
│   │   ├── java/school/sptech/FamiliaConnect/
│   │   │   ├── application/
│   │   │   │   ├── ports/in/      # Interfaces (contratos) dos casos de uso
│   │   │   │   └── service/       # Regras de negócio
│   │   │   ├── domain/
│   │   │   │   ├── entity/        # Entidades de domínio
│   │   │   │   ├── enums/         # Enumerações
│   │   │   │   └── exception/     # Exceções de domínio
│   │   │   └── infraestructure/
│   │   │       ├── config/            # Configurações (Security, OpenAPI, etc.)
│   │   │       ├── persistence/       # Repositórios JPA e implementações
│   │   │       └── web/
│   │   │           ├── client/        # Clients Feign (ex.: serviço de OCR)
│   │   │           ├── controller/    # Endpoints REST
│   │   │           ├── dto/           # Objetos de transferência de dados
│   │   │           ├── handler/       # Tratamento global de exceções
│   │   │           └── mapper/        # Conversão entre entidades e DTOs
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/school/sptech/FamiliaConnect/service/  # Testes unitários dos services
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

> O projeto segue uma arquitetura em camadas inspirada em Clean Architecture/Ports & Adapters: `domain` (regras e entidades), `application` (casos de uso/serviços) e `infraestructure` (web, persistência e configurações).

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
# Banco de dados
DB_URL=jdbc:mysql://localhost:3306/familia_connect
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
DB_TYPE_DDL=update

# Autenticação JWT
JWT_SECRET=uma_chave_secreta_com_no_minimo_32_caracteres

# Serviço de OCR
URL_OCR_SERVICE=http://localhost:8081

# Storage de arquivos (opcionais - possuem valores padrão)
APP_STORAGE_TYPE=local
APP_STORAGE_LOCAL_PATH=uploads
APP_STORAGE_S3_BUCKET=familia-connect-gold-bucket
APP_STORAGE_S3_REGION=us-east-1
```

#### 📌 Referência das variáveis

| Variável | Obrigatória | Padrão | Descrição |
|---|---|---|---|
| `DB_URL` | ✅ Sim | — | URL de conexão JDBC com o banco MySQL |
| `DB_USERNAME` | ✅ Sim | — | Usuário do banco de dados |
| `DB_PASSWORD` | ✅ Sim | — | Senha do banco de dados |
| `DB_TYPE_DDL` | ✅ Sim | — | Estratégia do Hibernate para o schema: `create`, `create-drop`, `update` ou `validate` |
| `JWT_SECRET` | ✅ Sim | — | Chave secreta usada para assinar os tokens JWT (mínimo de 32 caracteres) |
| `URL_OCR_SERVICE` | ✅ Sim | — | URL do serviço externo de OCR consumido via Feign |
| `APP_STORAGE_TYPE` | ❌ Não | `local` | Estratégia de armazenamento de arquivos: `local` ou `s3` |
| `APP_STORAGE_LOCAL_PATH` | ❌ Não | `uploads` | Diretório local usado quando `APP_STORAGE_TYPE=local` |
| `APP_STORAGE_S3_BUCKET` | ❌ Não | `familia-connect-gold-bucket` | Nome do bucket S3 usado quando `APP_STORAGE_TYPE=s3` |
| `APP_STORAGE_S3_REGION` | ❌ Não | `us-east-1` | Região da AWS do bucket S3 |

> **Dica:** O valor `DB_TYPE_DDL` pode ser `create`, `create-drop`, `update` ou `validate`. Para o primeiro uso, utilize `create` ou `update`.
>
> **Observação:** o `docker-compose.yml` e o `Dockerfile` já repassam `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `DB_TYPE_DDL`, `JWT_SECRET` e `URL_OCR_SERVICE` como variáveis de ambiente do container. Se precisar customizar o storage (`APP_STORAGE_*`), adicione as variáveis correspondentes em ambos os arquivos, ou passe-as com `--env-file .env` / `env_file: .env` no `docker-compose.yml`.

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
http://localhost:8080/api/swagger-ui/index.html
```

Ou via OpenAPI JSON:

```
http://localhost:8080/api/v3/api-docs
```

> Todos os endpoints da aplicação (incluindo Swagger) são servidos sob o prefixo `/api`, definido por `spring.mvc.servlet.path` no `application.properties`.

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

## 🤖 CI/CD

O workflow em `.github/workflows/pipeline.yml` roda no GitHub Actions:

- **CI** — a cada `push` ou `pull request` para `main`: sobe um serviço MySQL 8.0 no runner e executa `mvn clean test` com as credenciais do banco injetadas via secrets/vars do ambiente `development`.
- **CD** — disparado manualmente (`workflow_dispatch`, com a versão da imagem como parâmetro): após os testes passarem, builda a imagem Docker e publica no Docker Hub, taggeada com a versão informada e também como `latest`.

> Requer os secrets `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `DOCKERHUB_USERNAME` e `DOCKERHUB_TOKEN`, além da variável `DB_TYPE_DDL`, configurados no ambiente `development` do repositório.

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
