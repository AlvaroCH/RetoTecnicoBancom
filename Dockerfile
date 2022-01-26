# Start with a base image containing Java runtime
FROM adoptopenjdk:11-jdk-openj9

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8081 available to the world outside this container
EXPOSE 8081

# The application's jar file
ARG JAR_FILE=target/reto-bcp.jar

# Add the application's jar to the container
ADD ${JAR_FILE} reto-bcp.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/reto-bcp.jar"]