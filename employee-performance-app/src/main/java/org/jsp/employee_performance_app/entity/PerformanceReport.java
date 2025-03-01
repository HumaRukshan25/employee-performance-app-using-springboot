package org.jsp.employee_performance_app.entity;


import java.util.Map;
import lombok.*;

@Data
@AllArgsConstructor
public class PerformanceReport {
    private Map<Rating, Double> actualPercentage;
    private Map<Rating, Double> deviations;
}

