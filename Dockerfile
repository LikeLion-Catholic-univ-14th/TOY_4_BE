# 1단계: 빌드 스테이지 (openjdk 대신 지원 중단 없는 공식 이미지 사용)
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
# bootJAR -> bootJar로 오타 수정 (테스트 코드 제외하고 빌드 속도 향상 옵션 추가)
RUN ./gradlew bootJar -x test --no-daemon

# 2단계: 실행 스테이지 (마찬가지로 정상 지원되는 이미지로 교체)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
EXPOSE 8080
# builder 스테이지에서 설정한 WORKDIR(/app) 경로에 맞춰 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]