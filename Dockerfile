# Étape 1 : build du JAR avec Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copie des fichiers de configuration
COPY pom.xml .
COPY src ./src

# Compilation du projet
RUN mvn clean package -DskipTests

# Étape 2 : exécution du JAR
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copie du jar compilé depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar

# Port d'écoute
EXPOSE 8080

# Lancement de l’application
ENTRYPOINT ["java", "-jar", "app.jar"]
