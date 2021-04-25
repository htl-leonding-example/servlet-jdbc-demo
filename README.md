# servlet-jdbc-demo

## Iteration 1: Simple Servlets w/o database

### Start Server

```
mvn clean compile
java -jar jetty-servlet-demo.jar
```

### Stop Server

* Ctrl + C

## Iteration 2: Add postgres

### Start postgres w/ docker

```
./run-prostgres.sh
```

### Stop postgres w/ docker

```
docker stop postgres
```

## Iteration 3: Add docker-compose



## Check Endpoints

```
http://localhost:8081/api/person
http://localhost:8081/status
http://localhost:8081/

```







