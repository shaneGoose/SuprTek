# SUPRTEK coding challenge 2019

## Installation and Usage

### Spring Boot v2.5.0
The scaffold of this project is a RESTful application. 
Using the [spring initializr](https://start.spring.io/), the build was bundle as a gradle project and a local copy will allow operation without installation.
The application makes use of Spring's annotations and embedded Apache Tomcat server. 
The source code is under `src\main\java\com.guzman.z.shane.suprtek`

### Gradle v7.0.2
This project uses Gradle to manage the dependencies the project requires. 

### Usage

To run the Spring project:

#### Windows
`.\gradlew.bat bootRun`

#### Linux
`./gradlew bootRun`

Then navigate to [localHost:8080/report](http://localHost:8080/report) for a report on the top-level managers.
The project has a crude output on the browser for proof of concept.
Running the __DepartmentTest__.generateReport() will provide a Java String 
representation of the hierarchy. 

The query parameter `manager=<name>` will generate a report for that manager. Run the 
__DepartmentTest__.testGenerateReport() to get the Java String for that manager.
Replace <name> with the target manager, e.g. `http:/localHost:8080/managerReport?manager=A`
