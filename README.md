# Billing

This is a Java Spring Boot application that exposes REST APIs and gRPC services for dependency management. The project uses Gradle as the build tool.

## Prerequisites
Before running the application, you should have the following software installed:

- Java JDK (version 8 or higher)
- Gradle (version 6.0 or higher)

## Getting Started
To run the application, follow these steps:

- Clone the repository:
  `git clone https://github.com/shkelzeen/billing.git`


- Navigate to the project directory:
  `cd billing`
  
- Build the project:
  `gradle build`
  
- Run the application:
  `gradle bootRun` This will start the application on port 8080 by default.

- Test the application by sending a request to the REST endpoint or the gRPC service. You can use a tool like Postman or curl to send a request to the REST API endpoint or use a gRPC client to send a request to the gRPC service.

## Configuration

The application can be configured by modifying the application.properties file. You can change the port number, database connection details, and other settings in this file.

## API Documentation
The REST API endpoints are documented using Swagger UI. Once the application is running, you can access the Swagger UI documentation by navigating to http://localhost:8080/swagger-ui.html in your web browser.

## gRPC Service Documentation
The gRPC service is documented using Protocol Buffers. You can find the .proto files in the src/main/proto directory.

## Deployment
To deploy the application to a production environment, you can create a deployable artifact by running:
  `gradle build`

This will create a JAR file in the build/libs directory. You can then copy this JAR file to your production server and run it using the java -jar command.

##Conclusion
This is a basic template for a Java Spring Boot application that exposes REST APIs and gRPC services for dependency management. You can use this template as a starting point for your own projects.
