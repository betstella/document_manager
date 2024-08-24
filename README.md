# Readme

Java version 17
Maven version 3.8.3

## Compiling

```
mvn clean install

mvn package
```
## Running Project

The following command will build the project and run it in a docker container, which includes the database. 
All the configuration is inside the docker-compose.yml file.
```
docker compose up --build
```

To run the project you can use the following command:
```
mvn spring-boot:run
```

## Documentation

For API Documentation go to http://localhost:8080/swagger-ui/index.html

## Debug Configuration

To Debug the application you can use the following configuration in your IDE:

```
mvn spring-boot:run -Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
```

then configure a remote debug configuration in your IDE to connect to the port 5005.
```
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
```

## Postman Collection
https://gold-desert-307003.postman.co/workspace/Bet-Workspace~7a12b200-83e5-43cc-bb66-f7e916dc425f/collection/4223488-67da8f21-d193-42a7-9a0c-048c3c632a7b?action=share&creator=4223488