#!/bin/bash

gradle build && \
docker-compose build && \
docker-compose --env-file .env.docker up -d