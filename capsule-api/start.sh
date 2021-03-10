#!/bin/bash

gradle clean build && \
docker-compose build --no-cache && \
docker-compose --env-file .env.docker up -d