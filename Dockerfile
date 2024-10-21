# Sử dụng một image JDK 17
FROM openjdk:17-jdk-slim AS build

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file Gradle và build app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Chạy lệnh build
RUN ./gradlew build -x test

# Tạo image cho ứng dụng
FROM openjdk:17-jdk-slim

# Thiết lập thư mục cho ứng dụng
WORKDIR /app

# Sao chép file JAR từ build
COPY --from=build /app/build/libs/*.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
