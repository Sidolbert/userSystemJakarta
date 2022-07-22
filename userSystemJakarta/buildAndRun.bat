@echo off
call mvn clean package
call docker build -t com.csm.enrici/userSystemJakarta .
call docker rm -f userSystemJakarta
call docker run -d -p 9080:9080 -p 9443:9443 --name userSystemJakarta com.csm.enrici/userSystemJakarta