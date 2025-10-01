package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long>{
    List<WorkLog> findEmployeeWorkLogByEmployee(Employee employee);
}
