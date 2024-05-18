# Sıfır bir makinede (FREEDOS)
# Bu uygulamayı nasıl ayağa kaldırırım?

# 1. layer => bir layeri almak onun içindeki layerları da dahil eder.

# oracle linux
# openjdk 17
# maven 3.8.3
FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

RUN mkdir -p /root/.m2 && \
    echo "<?xml version='1.0' encoding='UTF-8'?><settings xmlns='http://maven.apache.org/SETTINGS/1.2.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd'><servers><server><id>github</id><username>${GITHUB_USERNAME}</username><password>${GITHUB_TOKEN}</password></server></servers></settings>" > /root/.m2/settings.xml

COPY . /app/
RUN mvn clean package -s /root/.m2/settings.xml -DskipTests

FROM openjdk:17-jdk
COPY --from=build /app/target/productservice-0.0.1-SNAPSHOT.jar /app/productservice.jar
# EXPOSE 8082
CMD java -jar /app/productservice.jar
