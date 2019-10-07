FROM openjdk:latest
ADD target/docker-url-shortener.jar docker-url-shortener.jar
RUN bash -c 'touch /docker-url-shortener.jar'
EXPOSE 8085
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-url-shortener.jar"]
