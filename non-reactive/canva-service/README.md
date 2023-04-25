## Como rodar

Para compilar o projeto (tem que compilar antes de rodar via docker)
```bash
mvn clean package -DskipTests
```

Para rodar o projeto usando docker
```bash
cd docker
docker-compose up -d
```

Para rodar o projeto sem docker
```bash
mvn spring-boot:run
```