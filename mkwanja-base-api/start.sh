#!/bin/bash

cd /root/mkwanja-base-api
git pull

CONTAINER_ID=$(docker ps | grep 'mkwanja_base_api:latest' | awk '{ print $1 }')
echo "Stopping Container with ID $CONTAINER_ID"
docker stop "$CONTAINER_ID"
docker container rm mkwanja_base


docker build -t mkwanja_base_api:latest .
docker volume prune -f
docker image prune -f
docker system prune -f

docker run -e "TZ=Africa/Dar_es_Salaam" -d -p 8080:8080 \
    --name=mkwanja_base \
    -v /root/files:/root/files -v /root/logs:/root/logs \
    mkwanja_base_api:latest