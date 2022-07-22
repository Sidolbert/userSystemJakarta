#!/bin/sh
mvn clean package && docker build -t com.csm.enrici/userSystemJakarta .
docker rm -f userSystemJakarta || true && docker run -d -p 9080:9080 -p 9443:9443 --name userSystemJakarta com.csm.enrici/userSystemJakarta