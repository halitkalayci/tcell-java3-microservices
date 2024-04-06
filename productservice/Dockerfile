# Sıfır bir makinede (FREEDOS)
# Bu uygulamayı nasıl ayağa kaldırırım?

# 1. layer => bir layeri almak onun içindeki layerları da dahil eder.

# oracle linux
# openjdk 17
# maven 3.8.3
FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=build /app/target/productservice-0.0.1-SNAPSHOT.jar /app/productservice.jar
EXPOSE 8082
CMD java -jar /app/productservice.jar
