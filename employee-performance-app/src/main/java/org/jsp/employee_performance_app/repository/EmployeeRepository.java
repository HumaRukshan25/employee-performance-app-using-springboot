
package org.jsp.employee_performance_app.repository;


import org.jsp.employee_performance_app.entity.Employee;
import org.jsp.employee_performance_app.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCategory(Rating category);  // Keep this method


	
}
