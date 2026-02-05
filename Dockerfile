# ===== build stage =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# 拷贝源码
COPY . .

# 构建 Spring Boot jar（跳过测试）
RUN mvn -q clean package -DskipTests


# ===== runtime stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# 先把 target 下所有 jar 拷贝过来
COPY --from=build /app/target/*.jar /app/target/

# 关键：选择“非 plain”的可执行 jar 作为 app.jar
RUN set -eux; \
    ls -lah /app/target; \
    JAR="$(ls /app/target/*.jar | grep -v plain | head -n 1)"; \
    echo "Using jar: $JAR"; \
    cp "$JAR" /app/app.jar

# Render 会提供 PORT 环境变量（一般是 10000）
EXPOSE 10000

# 用 Render 的 $PORT 启动
CMD ["sh","-c","java -jar /app/app.jar --server.port=${PORT:-10000}"]
