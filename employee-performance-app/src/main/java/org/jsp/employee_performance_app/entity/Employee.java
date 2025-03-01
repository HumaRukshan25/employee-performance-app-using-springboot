package org.jsp.employee_performance_app.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    
    private String name;
    private double salary;  
    
    @Enumerated(EnumType.STRING)
    private Rating category;  // A, B, C, D, E are rating category 
}
