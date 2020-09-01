# Ticket Service

This simple project consist of a simple api for creating, updating and fetching details of a ticket for fines such as speeding and parking.

#### Prerequisites:

If running from intellij, run with prod profile. This service assumes you have a mongodb server running on localhost at port 27017 (for simplicity it assumes there is no mongodb authentication required) 

### Build and run the application

To build the application run the following:

```
mvn package
```

To run application from the 'application' folder run:

```
mvn spring-boot:run
```

### Docker

The easiest way to run this application is to build via Docker. Once you have docker installed on your local repo, run the following command:

```
mvn clean install -Dddockerfile.skip=false
```

To launch the application via docker, cd to the docker directory and run

```
docker-compose up -d
```

When this is running you can access the following urls:

- Service API Documentation: http://localhost:8080
- Mongo Express: http://localhost:8081

The Service API contains all the API's and instructions on how to run them.
