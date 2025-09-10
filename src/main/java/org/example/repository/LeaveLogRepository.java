package org.example.repository;

import org.example.entity.LeaveLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveLogRepository extends JpaRepository<LeaveLog, Long> {
}
