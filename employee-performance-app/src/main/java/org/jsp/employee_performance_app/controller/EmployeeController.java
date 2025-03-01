package org.jsp.employee_performance_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jsp.employee_performance_app.entity.Employee;
import org.jsp.employee_performance_app.entity.PerformanceReport;
import org.jsp.employee_performance_app.responsestructure.ResponseStructure;
import org.jsp.employee_performance_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "This will Add a new employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee successfully added"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<Employee>> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
                .status(200)
                .message("Employee added successfully")
                .data(savedEmployee)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "This will Get all employees")
    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            ResponseStructure<List<Employee>> response = ResponseStructure.<List<Employee>>builder()
                    .status(404)
                    .message("No employees present")
                    .data(Collections.emptyList())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ResponseStructure<List<Employee>> response = ResponseStructure.<List<Employee>>builder()
                .status(200)
                .message("Employees fetched successfully")
                .data(employees)
                .build();
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "This will Get performance report")
    @GetMapping("/performance")
    public ResponseEntity<ResponseStructure<PerformanceReport>> getPerformanceReport() {
        PerformanceReport report = employeeService.calculatePerformance();
        ResponseStructure<PerformanceReport> response = ResponseStructure.<PerformanceReport>builder()
                .status(200)
                .message("Performance report generated successfully")
                .data(report)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "This will Get suggested rating revisions")
    @GetMapping("/suggestions")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getSuggestedRevisions() {
        Map<String, Object> revisions = employeeService.suggestRevisions();
        ResponseStructure<Map<String, Object>> response = ResponseStructure.<Map<String, Object>>builder()
                .status(200)
                .message("Suggested revisions retrieved successfully")
                .data(revisions)
                .build();
        return ResponseEntity.ok(response);
    }
    
    
    @Operation(summary = "This will Get employees by category")
    @GetMapping("/{category}")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeesByCategory(@PathVariable String category) {
        List<Employee> employees = employeeService.getEmployeesByCategory(category);

        if (employees.isEmpty()) {
            ResponseStructure<List<Employee>> response = ResponseStructure.<List<Employee>>builder()
                    .status(404)
                    .message("No employees found in this category or invalid category provided")
                    .data(Collections.emptyList())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ResponseStructure<List<Employee>> response = ResponseStructure.<List<Employee>>builder()
                .status(200)
                .message("Employees fetched successfully")
                .data(employees)
                .build();
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "This will Update an employee")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
                .status(200)
                .message("Employee updated successfully")
                .data(updatedEmployee)
                .build();
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "This will Delete an employee by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        ResponseStructure<String> response = ResponseStructure.<String>builder()
                .status(200)
                .message("Employee deleted successfully")
                .data("Employee with ID " + id + " has been deleted")
                .build();
        return ResponseEntity.ok(response);
    }

}



