#!/bin/sh

echo "Build the docker image"
docker build -t  spring-boot-docker-person .

echo "running the docker image as application"
docker run -p 8080:8080 spring-boot-docker-person