####
Employee Performance Appraisal System

Overview
The Employee Performance Appraisal System is a Spring Boot application that evaluates employee performance using a bell curve approach. It calculates performance deviations and suggests rating revisions based on predefined standards.

##
Tech Stack
Backend: Java, Spring Boot
Database: MySQL
ORM: Spring Data JPA (Hibernate)
API Documentation: Swagger (SpringDoc OpenAPI)
Dependencies:
MySQL Connector
Spring Boot DevTools
Lombok
Spring Web
Spring Data JPA
SpringDoc OpenAPI

1.Installation & Setup
download the repository from github

2.Import the Project
Open Spring Tool Suite (STS) or IntelliJ IDEA
Select File → Open Project and import the folder

3. Configure Database
Update application.properties in src/main/resources/:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run the Application
Run the EmployeePerformanceApp class from STS.


##
API Endpoints
Employee Management
Method	Endpoint	Description
POST	/employees/add	Add a new employee
GET	/employees/all	Get all employees
PUT	/employees/{id}	Update employee details

Performance Management
Method	Endpoint	Description
GET	/employees/performance	Get performance report
GET	/employees/suggestions	Get suggested rating revisions
GET	/employees/{category}	Get employees by rating category

##
Swagger API Documentation
After running the app, access Swagger UI at:
🔗 http://localhost:8080/swagger-ui/index.html



# Read Me First
The following was discovered as part of building this project:

* The original package name 'org.jsp.employee-performance-app' is invalid and this project uses 'org.jsp.employee_performance_app' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.3/reference/using/devtools.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.




