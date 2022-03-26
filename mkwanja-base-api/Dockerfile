FROM openjdk:15-jdk-slim as build

WORKDIR application

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN  chmod +x mvnw
RUN --mount=type=cache,target=/root/.m2 ./mvnw  install -DskipTests

RUN cp /application/target/*.jar mkwanja_base.jar
RUN java -Djarmode=layertools -jar mkwanja_base.jar extract


RUN addgroup mgapigroup && adduser  --ingroup mgapigroup --disabled-password mgapi
USER mgapi

FROM openjdk:15-jdk-slim
WORKDIR application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]