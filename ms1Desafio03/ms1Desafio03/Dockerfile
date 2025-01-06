# Dockerfile
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Porta exposta pelo microserviço
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
