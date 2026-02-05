# ===== build stage =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q clean package -DskipTests

# ===== runtime stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# 只拷贝你确认存在的可执行 jar（最稳）
COPY --from=build /app/target/medai-backend-0.0.1-SNAPSHOT.jar /app/app.jar

# Render 会注入 PORT（通常 10000）
EXPOSE 10000

CMD ["sh","-c","java -jar /app/app.jar --server.port=${PORT:-10000}"]
