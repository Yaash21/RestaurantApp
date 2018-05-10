FROM openjdk
VOLUME /tmp
ADD target/Restaurant-rest-0.0.1-SNAPSHOT.jar home/workspace/springboot-restaurant-app.jar
WORKDIR home/workspace/
RUN sh -c 'touch springboot-restaurant-app.jar'
ENTRYPOINT ["java", "-jar","springboot-restaurant-app.jar"]