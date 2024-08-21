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
All of the configuration is inside the docker-compose.yml file.
```
docker compose up --build
```

To run the project you can use the following command:
```
mvn spring-boot:run
```