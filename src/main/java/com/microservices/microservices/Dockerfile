# =========================
#  Build stage
# =========================
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy Gradle wrapper and configs first (for layer caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies (cached layer)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# Copy source code
COPY src src

# Build the executable jar
RUN ./gradlew bootJar --no-daemon


# =========================
#  Runtime stage
# =========================
FROM eclipse-temurin:21-jre

# Security best practice: non-root user
RUN useradd -ms /bin/bash springuser

WORKDIR /app

# Copy jar from build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership
RUN chown -R springuser:springuser /app

USER springuser

# Expose application port
EXPOSE 8080

# JVM options for containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]
