package org.jsp.employee_performance_app.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

// Swagger is an API testing and documentation tool  
	
// To use  swagger for api testing 
	// visit: http://localhost:8080/swagger-ui/index.html

    @Bean
    OpenAPI employeePerformanceOpenAPI() { 
        Server localhost = new Server();
        localhost.setUrl("http://localhost:8080");
        localhost.setDescription("Development environment");

        Contact contact = new Contact();
        contact.setEmail("rukshanhuma2508@gmail.com");
        contact.setName("Employee Performance API");
        contact.setUrl("http://localhost:8080");

        License mitLicense = new License().name("MIT License").url("http://localhost:8080");

        Info info = new Info()
                .title("Employee Performance Management API")
                .version("1.0")
                .contact(contact)
                .description("This API manages employee performance and ratings.")
                .termsOfService("http://localhost:8080")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(localhost));
    }
}
