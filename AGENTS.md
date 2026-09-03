# Familia Connect Backend - Agent Instructions

## Project Overview
Java 21 + Spring Boot 4.0.6 REST API for managing food basket donations. Uses MySQL, JWT auth, Feign clients, S3/local file storage.

## Key Commands

### Development
```bash
# Run locally (requires MySQL + .env)
./mvnw spring-boot:run          # Linux/macOS
mvnw.cmd spring-boot:run        # Windows

# Run tests
./mvnw test

# Build JAR (skip tests)
./mvnw clean package -DskipTests
java -jar target/FamiliaConnect-1.0.0.jar
```

### Docker
```bash
docker-compose up --build       # Build + run (needs .env)
docker-compose down             # Stop
```

### CI Pipeline (GitHub Actions)
- Runs on push/PR to `main`
- `mvn -B -q clean test` with MySQL service
- Docker build/push only on `workflow_dispatch` with version input

## Environment Variables (.env required)
```
DB_URL=jdbc:mysql://localhost:3306/familia_connect
DB_USERNAME=root
DB_PASSWORD=your_password
DB_TYPE_DDL=update              # create | create-drop | update | validate
URL_OCR_SERVICE=localhost:5000  # Feign client for OCR microservice

# Optional storage config
APP_STORAGE_TYPE=local          # local | s3
APP_STORAGE_LOCAL_PATH=uploads
APP_STORAGE_S3_BUCKET=familia-connect-gold-bucket
APP_STORAGE_S3_REGION=us-east-1
```

## Architecture Notes
- **Package**: `school.sptech.FamiliaConnect`
- **Entry point**: `FamiliaConnectApplication.java` with `@EnableFeignClients`
- **Layers**: controller → service → repository → entity (JPA)
- **DTOs + Mappers** per domain (e.g., `FamiliaMapper`, `FamiliaRequestDto`, `FamiliaResponseDto`)
- **Security**: JWT in cookies via `GerenciadorTokenJwt`, `AutenticacaoFilter`, `SecurityConfiguracao`
- **Exceptions**: Custom exceptions in `exception/` (e.g., `EntidadeNaoEncontradaException`, `EntidadeJaCadastradaException`)
- **File storage**: Strategy pattern (`ArquivoService` with `ArquivoLocalServiceImpl` / `ArquivoS3ServiceImpl`)

## Testing
- JUnit 5 + Mockito (`@ExtendWith(MockitoExtension.class)`)
- Tests in `src/test/java/school/sptech/FamiliaConnect/service/`
- Nested test classes with `@DisplayName` for readability
- Run single test: `./mvnw test -Dtest=FuncionarioServiceTest#listar`

## API Docs
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Ports
- App: 8080
- MySQL: 3306 (docker-compose)

## Gotchas
- Lombok requires annotation processor config in `pom.xml`
- JWT secret in `application.properties` must be ≥32 chars (base64 encoded)
- `spring-dotenv` loads `.env` automatically
- Max file upload: 10MB (configured in `application.properties`)
- Pagination max page size: 50