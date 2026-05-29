# 1. Base 이미지 지정 (JDK 17 기준)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 JAR 파일을 컨테이너 내부로 복사
# Gradle 빌드 시 보통 build/libs/ 폴더 안에 -SNAPSHOT.jar 파일이 생성됩니다.
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 4. 컨테이너가 실행될 때 실행할 명령어 지정
ENTRYPOINT ["java", "-jar", "app.jar"]