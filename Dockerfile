FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} document_manager.jar
CMD apt-get update -y
EXPOSE 8080
EXPOSE 5434
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/document_manager.jar"]