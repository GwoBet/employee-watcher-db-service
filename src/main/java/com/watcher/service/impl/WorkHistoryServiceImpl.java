package com.watcher.service.impl;

import com.watcher.dto.WorkHistoryDTO;
import com.watcher.dto.mapper.WorkHistoryMapper;
import com.watcher.exceptions.BaseDBSException;
import com.watcher.repository.WorkHistoryRepository;
import com.watcher.service.WorkHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkHistoryServiceImpl implements WorkHistoryService {

    private final WorkHistoryRepository workHistoryRepository;
    private final WorkHistoryMapper mapper;

    @Autowired
    public WorkHistoryServiceImpl(
            WorkHistoryRepository workHistoryRepository,
            WorkHistoryMapper mapper
    ) {
        this.workHistoryRepository = workHistoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void updateWork(WorkHistoryDTO workHistoryDTO) {
        try {
            workHistoryRepository.save(mapper.from(workHistoryDTO));
        } catch (Exception e) {
            log.error("WorkHistory object update failed. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("WorkHistory object update failed. Reason: " + e.getMessage(), e);
        }
    }

}
