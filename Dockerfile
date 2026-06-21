# Use a Maven image with java 17 to build spring boot app
FROM eclipse-temurin:17-jdk AS build

#set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY mvnw ./
COPY .mvn/ .mvn/

# ensure the maven wrapper is executable
RUN chmod +x mvnw

#coopy the pom.cml and install dependencies
COPY pom.xml ./
RUN ./mvnw dependency:go-offline

#copy the source code and build the application
COPY src ./src
RUN ./mvnw clean package -DskipTests

#use a java 17 runtime image to run the application
FROM eclipse-temurin:17-jre

# set the working directory
WORKDIR /app

#copy the build JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]