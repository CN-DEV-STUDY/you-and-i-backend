# jdk17 Image Start
FROM openjdk:17

# 인자 설정 - JAR_File
ARG JAR_FILE=build/libs/*.jar

# jar 파일 복제
COPY ${JAR_FILE} app.jar

# 인자 설정 부분과 jar 파일 복제 부분 합쳐서 진행해도 무방
#COPY build/libs/*.jar app.jar

# "application-dev.yml"을 Docker 이미지로 복사
COPY /src/main/resources/application-dev.yml application-dev.yml

# 실행 명령어
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-Dspring.config.location=classpath:/application.yml,classpath:/application-dev.yml", "-jar", "app.jar"]
