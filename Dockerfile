FROM amazoncorretto:11.0.13
CMD ["echo","RUNNING COMPLETE"]
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/wirebarley-1.0-SNAPSHOT.jar"]

### Project Build
#FROM amazoncorretto:11.0.13 AS builder
#COPY gradlew .
#COPY gradle gradle
#COPY settings.gradle .
#COPY build.gradle .
#COPY src src
#RUN chmod 777 ./gradlew
#RUN  ./gradlew bootJar

#FROM amazoncorretto:11.0.13
#COPY --from=builder build/libs/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","bootJar.jar"]


### MYSQL
#FROM mysql:5.7
#MAINTAINER camper <vlll3320@gmail.com>
#EXPOSE 3306
#CMD ["mysqld"]
