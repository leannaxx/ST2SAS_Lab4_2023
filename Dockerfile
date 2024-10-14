FROM openjdk:11-jre-slim
WORKDIR /app
COPY ProgApp.jar /app/ProgApp.jar
COPY lib /app/lib
ENTRYPOINT ["java", "-cp", "/app/ProgApp.jar:/app/lib/*", "st2sas.lab4.exec.Start"]
