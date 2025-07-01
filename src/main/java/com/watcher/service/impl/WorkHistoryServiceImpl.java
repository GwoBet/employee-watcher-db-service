package com.watcher.service.impl;

import com.watcher.dto.WorkHistoryDTO;
import com.watcher.dto.mapper.WorkHistoryMapper;
import com.watcher.repository.WorkHistoryRepository;
import com.watcher.service.WorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        workHistoryRepository.save(mapper.from(workHistoryDTO));
    }

}
