FROM openjdk:8
ARG WAR_FILE=target/*.war
WORKDIR /opt/app
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.war", \
"--spring.jpa.hibernate.ddl-auto=${JPA_HIBERNATE_DDL_AUTO}", \
"--spring.datasource.url=jdbc:postgresql://${POSTGRESQL_HOST}:5432/${POSTGRESQL_DATABASE}",  \
"--spring.datasource.username=${POSTGRESQL_USERNAME}", \
"--spring.datasource.password=${POSTGRESQL_PASSWORD}"]
