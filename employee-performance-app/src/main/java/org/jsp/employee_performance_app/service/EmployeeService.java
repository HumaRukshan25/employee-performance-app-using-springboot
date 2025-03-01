

package org.jsp.employee_performance_app.service;


import org.jsp.employee_performance_app.entity.Employee;
import org.jsp.employee_performance_app.entity.PerformanceReport;
import org.jsp.employee_performance_app.entity.Rating;
import org.jsp.employee_performance_app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Map<Rating, Integer> STANDARD_PERCENTAGE = Map.of(
        Rating.A, 10, Rating.B, 20, Rating.C, 40, Rating.D, 20, Rating.E, 10
    );

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public PerformanceReport calculatePerformance() {
        List<Employee> employees = employeeRepository.findAll();
        int totalEmployees = employees.size();
        
        Map<Rating, Integer> actualDistribution = new HashMap<>();
        for (Rating r : Rating.values()) {
            actualDistribution.put(r, 0);
        }

        for (Employee emp : employees) {
            actualDistribution.put(emp.getCategory(), actualDistribution.get(emp.getCategory()) + 1);
        }

        Map<Rating, Double> actualPercentage = new HashMap<>();
        for (Rating r : Rating.values()) {
            actualPercentage.put(r, (actualDistribution.get(r) * 100.0) / totalEmployees);
        }

        Map<Rating, Double> deviations = new HashMap<>();
        for (Rating r : Rating.values()) {
            deviations.put(r, actualPercentage.get(r) - STANDARD_PERCENTAGE.get(r));
        }

        return new PerformanceReport(actualPercentage, deviations);
    }


//    public List<Employee> suggestRevisions() {
//        PerformanceReport report = calculatePerformance();
//        List<Employee> employees = employeeRepository.findAll();
//        List<Employee> toBeRevised = new ArrayList<>();
//
//        for (Employee emp : employees) {
//            double deviation = report.getDeviations().get(emp.getCategory());
//
//            // If deviation is greater than +5%, downgrade some employees in that category
//            if (deviation > 5) { 
//                toBeRevised.add(emp);
//            }
//
//            // If deviation is less than -5%, upgrade some employees in that category
//            else if (deviation < -5) { 
//                toBeRevised.add(emp);
//            }
//        }
//
//        return toBeRevised;
//    }

public Map<String, Object> suggestRevisions() {
    PerformanceReport report = calculatePerformance();
    List<Employee> employees = employeeRepository.findAll();
    List<Employee> toBeUpgraded = new ArrayList<>();
    List<Employee> toBeDowngraded = new ArrayList<>();

    for (Employee emp : employees) {
        double deviation = report.getDeviations().get(emp.getCategory());

        if (deviation > 5) {  // many employees in this category so downgrade some
            toBeDowngraded.add(emp);
        } else if (deviation < -5) {  //  few employees in this category so  upgrade some
            toBeUpgraded.add(emp);
        }
    }

    Map<String, Object> response = new HashMap<>();
    if (toBeUpgraded.isEmpty() && toBeDowngraded.isEmpty()) {
        response.put("message", "No employees need rating revisions. The current rating distribution is balanced.");
    } else {
        if (!toBeUpgraded.isEmpty()) {
            response.put("upgrade suggestion", "Consider increasing the ratings for the following employees.");
            response.put("employees to be upgraded", toBeUpgraded);
        }
        if (!toBeDowngraded.isEmpty()) {
            response.put("downgrade suggestion", "Consider decreasing the ratings for the following employees.");
            response.put("employees to be downgraded", toBeDowngraded);
        }
    }

    return response;
}

public List<Employee> getEmployeesByCategory(String categoryName) {
    try {
        Rating category = Rating.valueOf(categoryName.toUpperCase()); // Convert String to Enum
        return employeeRepository.findByCategory(category);
    } catch (IllegalArgumentException e) {
        return Collections.emptyList(); // Return empty list if category is invalid
    }
}



public Employee updateEmployee(Long id, Employee updatedEmployee) {
    return employeeRepository.findById(id)
            .map(employee -> {
                employee.setName(updatedEmployee.getName());
                employee.setSalary(updatedEmployee.getSalary()); 
                employee.setCategory(updatedEmployee.getCategory());
                return employeeRepository.save(employee);
            })
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
}

public void deleteEmployee(Long id) {
    if (!employeeRepository.existsById(id)) {
        throw new RuntimeException("Employee not found with id: " + id);
    }
    employeeRepository.deleteById(id);
}


}

