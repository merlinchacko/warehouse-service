FROM openjdk:8
EXPOSE 8080
ENV JASYPT_PASSWORD=dummy
RUN mkdir -p /app/
ADD build/libs/warehouse-service-0.0.1-SNAPSHOT.jar /app/warehouse-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=${JASYPT_PASSWORD}", "-jar", "/app/warehouse-service-0.0.1-SNAPSHOT.jar"]