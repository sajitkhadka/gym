FROM openjdk:17-jdk-alpine

ARG SPRING_PROFILES_ACTIVE=prod
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

WORKDIR /app

# Copy the jar file into the docker image
COPY ./target/*.jar /app/gym.jar

EXPOSE 8081

# Run the jar file
CMD ["java", "-jar", "gym.jar"]
