#!/usr/bin/env bash

mvn clean package

mv ./target/jetty-servlet-demo.jar ./docker

cd ./docker

ls -l
docker-compose build
docker-compose up -d
docker-compose logs