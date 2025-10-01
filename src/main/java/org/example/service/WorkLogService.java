package org.example.service;

import org.example.entity.WorkLog;
import org.example.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkLogService {
    private final WorkLogRepository workLogRepository;

    public WorkLogService(WorkLogRepository workLogRepository) {
        this.workLogRepository = workLogRepository;
    }

    public List<WorkLog> getAllWorkLogs() {
        return workLogRepository.findAll();
    }
}
