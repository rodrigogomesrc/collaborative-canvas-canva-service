FROM openjdk:19
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar", \
"--spring.jpa.hibernate.ddl-auto=${JPA_HIBERNATE_DDL_AUTO}", \
"--spring.datasource.url=jdbc:postgresql://${POSTGRESQL_HOST}:5432/${POSTGRESQL_DATABASE}",  \
"--spring.datasource.username=${POSTGRESQL_USERNAME}", \
"--spring.datasource.password=${POSTGRESQL_PASSWORD}"]
