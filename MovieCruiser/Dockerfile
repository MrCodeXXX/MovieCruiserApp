FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=movie_database
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/movie_database
ADD ./target/moviecruiser-0.0.1-SNAPSHOT.jar /usr/src/moviecruiser-0.0.1-SNAPSHOT.jar
EXPOSE 1111
ENTRYPOINT ["java","-jar","moviecruiser-0.0.1-SNAPSHOT.jar"]
