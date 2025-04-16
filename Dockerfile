FROM amazoncorretto:17.0.14

COPY . /app/

WORKDIR /app/

RUN ./gradlew build

WORKDIR /app/build/libs

CMD ["java", "-jar", "Capitole-Code-Test-1.0.0.jar"]
