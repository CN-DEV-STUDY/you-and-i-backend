IMAGE_FILE_PATH="/home/ec2-user/action/image.txt"
IMAGE_NAME=$(cat "$IMAGE_FILE_PATH")
SERVICE_NAME=you-and-i-backend
CONTAINER_NAME=you-and-i-backend

# Docker Compose YAML을 새로운 도커 버전으로 작성해서 저장
echo "version: '3.8'
services:
  you-and-i-backend:
    container_name: ${CONTAINER_NAME}
    image: ${IMAGE_NAME}
    ports:
      - 8080:8080" > docker-compose.yml

# 새로운 도커 컨테이너 실행
echo "IMAGE_NAME: ${IMAGE_NAME} 도커 실행"
docker-compose up -d ${SERVICE_NAME}