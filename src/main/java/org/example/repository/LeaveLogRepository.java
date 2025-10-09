package org.example.repository;

import org.example.entity.Employee;
import org.example.entity.LeaveLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveLogRepository extends JpaRepository<LeaveLog, Long> {
    List<LeaveLog> findByEmployee(Employee employee);
}
