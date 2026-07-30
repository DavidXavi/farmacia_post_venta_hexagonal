FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY pos-domain/pom.xml pos-domain/pom.xml
COPY pos-application/pom.xml pos-application/pom.xml
COPY pos-adapter-in-rest/pom.xml pos-adapter-in-rest/pom.xml
COPY pos-adapter-out-persistence/pom.xml pos-adapter-out-persistence/pom.xml
COPY pos-adapter-out-external/pom.xml pos-adapter-out-external/pom.xml
COPY pos-bootstrap/pom.xml pos-bootstrap/pom.xml
RUN chmod +x mvnw && ./mvnw -q -B dependency:go-offline
COPY . .
RUN ./mvnw -q -B -DskipTests package

FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /app/pos-bootstrap/target/pos-farmacia.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
