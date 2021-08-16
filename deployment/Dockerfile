FROM openjdk:14.0.1
LABEL version="1.0.0" maintainer="anderson.vegacastillo@gmail.com.co" description="Mutant Analyzer"
EXPOSE 8085
COPY applications/app-service/build/libs/app-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]