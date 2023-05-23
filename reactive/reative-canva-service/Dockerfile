FROM openjdk:19
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","--enable-preview","-jar","app.jar", \
"--app.database.drop=${DROP_TABLES}", \
"--app.database.create=${CREATE_TABLES}", \
"--spring.r2dbc.url=r2dbc:postgresql://${POSTGRESQL_HOST}:5432/${POSTGRESQL_DATABASE}",  \
"--spring.r2dbc.username=${POSTGRESQL_USERNAME}", \
"--spring.r2dbc.password=${POSTGRESQL_PASSWORD}"]
